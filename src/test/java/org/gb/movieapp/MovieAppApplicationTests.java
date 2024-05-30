package org.gb.movieapp;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.Repository.*;
import org.gb.movieapp.Utils.RandomColor;
import org.gb.movieapp.entites.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    MovieAppRepository movieAppRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void saveBlog() {
        Faker faker = new Faker(new Locale("en_US"));
        Slugify slugify = Slugify.builder().build();
        List<User> users = userRepository.findByRole(UserRole.ADMIN);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            String name = faker.lorem().paragraph(1);
            String color = RandomColor.getRandomColor();
            String title = faker.book().title();
            Blogs blog = Blogs.builder()
                    .title(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph(5))
                    .content(faker.lorem().paragraph(45))
                    .thumbnail("https://placehold.co/600x400/"+color+ "/FFF" + "?text=" + String.valueOf(title.charAt(0)).toUpperCase())
                    .status(faker.bool().bool())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .user(users.get(random.nextInt(users.size())))
                    .build();

            blogRepository.save(blog);
        }
    }

@Test
void saveGenre() {
    Faker faker = new Faker();
    Slugify slugify = Slugify.builder().build();
    List<String> genres = List.of("Action", "Adventure", "Comedy", "Crime", "Drama", "Fantasy", "Historical", "Horror", "Mystery", "Romance", "Science Fiction", "Thriller", "Western");
    for (int i = 0; i < 12; i++) {
        String name = genres.get(i);
         Genre genre = Genre.builder()
                .name(name)
                .slug(slugify.slugify(name))
                .build();
         genreRepository.save(genre);
    }
}
@Test
void saveActors()   {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 40; i++) {
            String name = faker.name().fullName();
            Actors actor = Actors.builder()
                    .name(name)
                    .avatar("https://placehold.co/600x400/000/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .build();
            actorRepository.save(actor);
        }
}

@Test
void saveDirector() {
    Faker faker = new Faker();
    Slugify slugify = Slugify.builder().build();
    for (int i = 0; i < 40; i++) {
        String name = faker.name().fullName();
        Directors director = Directors.builder()
                .name(name)
                .avatar("https://placehold.co/600x400/000/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                .bio(faker.lorem().paragraph())
                .build();
        directorRepository.save(director);
    }
}
@Test
void saveCountry() {
    Faker faker = new Faker();
    Slugify slugify = Slugify.builder().build();
    for (int i = 0; i < 12; i++) {
        String name = faker.country().name();
        Country country = Country.builder()
                .name(name)
                .slug(slugify.slugify(name))
                .build();
        countryRepository.save(country);
    }
}

@Test
void saveComment() {
    Faker faker = new Faker(new Locale("en"));
    Random random = new Random();
    List<User> users = userRepository.findByRole(UserRole.USER);
    List<Blogs> blogs = blogRepository.findAll();
    for (Blogs blog : blogs) {
        for (int i = 0; i < random.nextInt(5) + 5; i++) {
            String content = faker.lorem().paragraph();
            Comments comment = Comments.builder()
                    .content(content)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .user(users.get(random.nextInt(users.size())))
                    .blog(blog)
                    .build();
            commentRepository.save(comment);
        }
    }
}


    @Test
    void saveMovie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        List<Country> countries = countryRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        List<Actors> actors = actorRepository.findAll();
        List<Directors> directors = directorRepository.findAll();

        Random random = new Random();

        for (int i = 0; i < 140; i++) {
            String color = RandomColor.getRandomColor();
            String name = faker.book().title();
            Country  country = countries.get(random.nextInt(countries.size()));

            List<Genre> genreList = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 4; j++) {
                Genre gd = genres.get(random.nextInt(genres.size()));
                if (!genreList.contains(gd)) {
                    genreList.add(gd);
                }
            }

            List<Actors> actorList = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Actors actor = actors.get(random.nextInt(actors.size()));
                if (!actorList.contains(actor)) {
                    actorList.add(actor);
                }
            }

            List<Directors> directorList = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Directors director = directors.get(random.nextInt(directors.size()));
                if (!directorList.contains(director)) {
                    directorList.add(director);
                }
            }


            Movies movie = Movies.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400/" + color + "/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2020, 2024))
                    .rating(faker.number().randomDouble(1, 1, 10))
                    .type(faker.options().option(MovieType.values()))
                    .status(faker.bool().bool())
                    .trailer("https://res.cloudinary.com/dll5rlqx9/video/upload/v1716637700/samples/dance-2.mp4")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .actors(actorList)
                    .country(country)
                    .directors(directorList)
                    .genres(genreList)
                    .build();

            movieAppRepository.save(movie);
        }
    }

@Test
void saveEpisodes() {
    Faker faker = new Faker();
    Slugify slugify = Slugify.builder().build();
    for (int i = 0; i < 20; i++) {
        String name = faker.book().title();
        Episodes episodes = Episodes.builder()
                .name(name)
                .video_url("https://www.youtube.com/embed/EzFXDvC-EwM?si=lsC6njoE1fD3Zag5")
                .orders(i)
                .build();
        episodeRepository.save(episodes);
    }
}

@Test
void saveUser() {
    Faker faker = new Faker();
    for (int i = 0; i < 49; i++) {
        String randomColor =  RandomColor.getRandomColor();
        String name = faker.name().fullName();
        User user = User.builder()
                .name(name)
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .avatar("https://placehold.co/600x400/"+randomColor+ "/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                .role(i==0 || i==1 ? UserRole.ADMIN : UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }
    }


    @Test
    void updatePasswordUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPassword(bCryptPasswordEncoder.encode("123"));
            userRepository.save(user);
        }
    }

    @Test
    void save_Review() {
        Faker faker = new Faker();
        Random random = new Random();
        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movies> movies = movieAppRepository.findAll();
        for (Movies movie : movies) {
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                String content = faker.lorem().paragraph(2);
                Reviews review = Reviews.builder()
                        .content(content)
                        .rating(random.nextInt(10)+1)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(users.get(random.nextInt(users.size())))
                        .movies(movie)
                        .build();
                reviewRepository.save(review);
            }
        }
    }

    @Test
    void save_Favourite() {
        Random random = new Random();
        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movies> movies = movieAppRepository.findAll();
        for (User user : users) {
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                Favourites favourite = Favourites.builder()
                        .user(user)
                        .movies(movies.get(random.nextInt(movies.size())))
                        .createdAt(LocalDateTime.now())
                        .build();
                favouriteRepository.save(favourite);
            }
        }
    }

    @Test
    void save_History() {
        Random random = new Random();
        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movies> movies = movieAppRepository.findAll();
        for (User user : users) {
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                History history = History.builder()
                        .user(user)
                        .movies(movies.get(random.nextInt(movies.size())))
                        .build();
                historyRepository.save(history);
            }
        }
    }

    @Test
    void save_Episode() {
        Random random = new Random();
        Faker faker = new Faker();
        List<Movies> movies = movieAppRepository.findAll();
        for (Movies movie : movies) {
            if (movie.getType().equals(MovieType.TVSHOWS)) {
                for (int i = 1; i < random.nextInt(6) + 7; i++) {
                    Episodes episode = Episodes.builder()
                            .name("Episode " + (i + 1) + ": " + faker.book().title())
                            .duration(random.nextInt(30) + 50)
                            .video_url("https://res.cloudinary.com/dll5rlqx9/video/upload/v1716637700/samples/dance-2.mp4")
                            .orders(i)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .movies(movie)
                            .build();
                    episodeRepository.save(episode);
                }
            }
            else {
                    Episodes episode = Episodes.builder()
                            .name(movie.getName())
                            .duration(random.nextInt(140) + 20)
                            .video_url("https://res.cloudinary.com/dll5rlqx9/video/upload/v1716637700/samples/dance-2.mp4")
                            .orders(1)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .movies(movie)
                            .build();
                    episodeRepository.save(episode);
            }
        }
    }

    @Test
    void test_pagination() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Movies> page = movieAppRepository.findByStatus(true, pageRequest);

        System.out.println("Total pages: " + page.getTotalPages());
        page.getContent().forEach(movies -> System.out.println(movies.getName()));
    }


}
