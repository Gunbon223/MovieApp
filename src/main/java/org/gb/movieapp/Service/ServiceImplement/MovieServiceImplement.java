package org.gb.movieapp.Service.ServiceImplement;

import com.github.slugify.Slugify;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Exception.ResourceNotFoundException;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Model.Request.UpsertMovieRequest;
import org.gb.movieapp.Repository.*;
import org.gb.movieapp.Service.*;
import org.gb.movieapp.Utils.RandomColor;
import org.gb.movieapp.entites.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImplement implements MovieService
{
    @Autowired
    MovieAppRepository movieAppRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryService countryService;
    @Autowired
    ActorService actorService;
    @Autowired
    GenreService genreService;
    @Autowired
    DirectorService directorService;
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    CloudinaryService cloudinaryService;


    @Override
    public Movies getById(int id) {
        return movieAppRepository.findById(id);
    }

    @Override
    public Movies getByIdAndSlug(int id, String slug) {
        return movieAppRepository.findByIdAndSlug(id,slug);
    }

    @Override
    public Page<Movies> findByTypeAndStatus(MovieType type, boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("CreatedAt").descending());
        return movieAppRepository.findByTypeAndStatus(type, status, pageRequest);
    }

    @Override
    public Page<Movies> findByStatus(boolean status, Pageable pageable) {
        return movieAppRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Movies> OrderByRatingDesc(boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("CreatedAt").descending());
        return movieAppRepository.findByStatusOrderByRatingDesc(pageRequest, status);
    }

    @Override
    public List<Movies> getAllDesc() {
        return movieAppRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Movies createMovie(UpsertMovieRequest request) {
        String color = RandomColor.getRandomColor();
        Slugify slg = Slugify.builder().build();
        Movies movies = new Movies().builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(MovieType.valueOf(request.getType()))
                .slug(slg.slugify(request.getName()))
                .status(request.getStatus())
                .rating((double) 0)
                .releaseYear(request.getReleaseYear())
                .poster("https://placehold.co/600x400/" + color + "/FFF" + "?text=" + String.valueOf(request.getName().charAt(0)).toUpperCase())
                .trailer(request.getTrailer())
                .country(countryService.getCountryById(request.getCountryId()))
                .actors(actorService.getActorByIds(request.getActors()))
                .genres(genreService.getGenreByIds(request.getGenres()))
                .directors(directorService.getDirectorByIds(request.getDirectors()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return movieAppRepository.save(movies);
    }

    @Override
    public Movies updateMovie(UpsertMovieRequest request, int id) {
        Slugify slg = Slugify.builder().build();
        Movies movies = movieAppRepository.findById(id);
        if (movies == null) {
            throw new ResourceNotFoundException("Movie not found with id " + id);
        }
        movies.setId(id);
        movies.setName(request.getName());
        movies.setDescription(request.getDescription());
        movies.setType(MovieType.valueOf(request.getType()));
        movies.setSlug(slg.slugify(request.getName()));
        movies.setStatus(request.getStatus());
        movies.setReleaseYear(request.getReleaseYear());
        movies.setTrailer(request.getTrailer());
        movies.setCountry(countryService.getCountryById(request.getCountryId()));
        movies.setActors(actorService.getActorByIds(request.getActors()));
        movies.setGenres(genreService.getGenreByIds(request.getGenres()));
        movies.setDirectors(directorService.getDirectorByIds(request.getDirectors()));
        movies.setUpdatedAt(LocalDateTime.now());

        return movieAppRepository.save(movies);
    }

    @Override
    @Transactional
    public void deleteMovie(Integer movieId) {
        // Delete the episodes that reference the movie
        episodeRepository.deleteByMovies_Id(movieId);
        favouriteRepository.deleteByMovies_Id(movieId);
        historyRepository.deleteByMovies_Id(movieId);
        reviewRepository.deleteByMovies_Id(movieId);

        // Delete the movie
        movieAppRepository.deleteById(movieId);
    }

    @Override
    public String uploadThumbnail(int id, MultipartFile file) {
        Movies movies = movieAppRepository.findById(id);
        if (movies == null) {
            throw new ResourceNotFoundException("Không tìm thấy movie");
        }
        try {
            Map data = cloudinaryService.uploadFile(file, "moviePoster");
            String url = (String) data.get("url");
            movies.setPoster(url);
            movieAppRepository.save(movies);
            return url;

        } catch (Exception e) {
            throw new BadRequestException("Không thể upload ảnh");
        }
    }

    }



