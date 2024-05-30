package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Exception.ResourceNotFoundException;
import org.gb.movieapp.Model.Request.UpsertEpisodeRequest;
import org.gb.movieapp.Model.Request.UpsertFullEpisodeRequest;
import org.gb.movieapp.Repository.EpisodeRepository;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Service.CloudinaryService;
import org.gb.movieapp.Service.EpisodeService;
import org.gb.movieapp.entites.Episodes;
import org.gb.movieapp.entites.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeImplements implements EpisodeService {
    @Autowired
    EpisodeRepository episodesRepository;
    @Autowired
    MovieAppRepository movieAppRepository;
    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public List<Episodes> episodesByMovieId(int movieId) {
        return episodesRepository.findEpisodesByMovies(movieAppRepository.findById(movieId));
    }

    @Override
    public List<Episodes> getEpisodesByMovieId(int id) {
        return episodesRepository.findByMovies_Id(id);
    }

    @Override
    public Episodes getEpisodeById(int id) {
        return episodesRepository.findById(id);
    }

    @Override
    public Episodes createEpisode(UpsertEpisodeRequest request, int movieId) {
        Movies movies = movieAppRepository.findById(movieId);
        if (movies == null) {
            throw new ResourceNotFoundException("Movie not found");
        }

        Episodes episodes = new Episodes();
        episodes.setName(request.getName());
        episodes.setOrders(request.getOrder());
        episodes.setMovies(movies);
        episodes.setCreatedAt(LocalDateTime.now());
        episodes.setUpdatedAt(LocalDateTime.now());
        return episodesRepository.save(episodes);
    }

    @Override
    public Episodes updateEpisode(UpsertFullEpisodeRequest request, int episodeId) {
        Episodes episodes = episodesRepository.findById(episodeId);
        episodes.setName(request.getName());
        episodes.setOrders(request.getOrder());
        episodes.setUpdatedAt(LocalDateTime.now());
        episodes.setVideo_url(request.getVideo_url());
        episodes.setDuration(request.getDuration());
        return episodesRepository.save(episodes);
    }

    @Override
    public void deleteEpisode(int episodeId) {
        episodesRepository.delete(episodesRepository.findById(episodeId));
    }

    @Override
    public String uploadVideo(int episodeId, MultipartFile file) {
        Episodes episodes = episodesRepository.findById(episodeId);
        if (episodes == null) {
            throw new ResourceNotFoundException("Không tìm thấy episode");
        }
        try {
            Map data = cloudinaryService.uploadVideo(file, "videoFilm");
            String url = (String) data.get("url");
            episodes.setVideo_url(url);
            episodes.setUpdatedAt(LocalDateTime.now());
            episodesRepository.save(episodes);
            return url;

        } catch (Exception e) {
            throw new BadRequestException("Không thể upload video");
        }
    }

    @Override
    public Episodes getEpisode(Integer movieId, String tap) {
        if (tap.equals("full")) {
            return episodesRepository
                    .findByMovies_IdAndMovies_StatusAndOrders(movieId, true, 1)
                    .orElse(null);
        } else {
            return episodesRepository
                    .findByMovies_IdAndMovies_StatusAndOrders(movieId, true, Integer.parseInt(tap))
                    .orElse(null);
        }
    }


}
