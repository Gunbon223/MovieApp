package org.gb.movieapp;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.entites.Movies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MovieAppApplicationTests {

    @Autowired
    private MovieAppRepository movieAppRepository;

    @Test
    void contextLoads() {
    }
    @Autowired
    MovieAppRepository MovieAppRepository;

    @Test
    void saveMovie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 100; i++) {
            String name = faker.book().title();
            Movies movie = Movies.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2020, 2024))
                    .rating(faker.number().randomDouble(1, 1, 10))
                    .type(faker.options().option(MovieType.values()))
                    .status(faker.bool().bool())
                    .trailer("https://www.youtube.com/embed/EzFXDvC-EwM?si=lsC6njoE1fD3Zag5")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            movieAppRepository.save(movie);
        }
    }

    @Test
    void test_Movie_Query() {
        List<Movies> movies = movieAppRepository.findAll();
        System.out.println("Size: " + movies.size());

        //
        List <Movies> moviesById = movieAppRepository.findAllById(List.of(1, 2, 3));

        //
        Movies movies1 = movieAppRepository.findById(212).orElse(null);
        System.out.println(movies1);

        movies1.setName("Leauge of Legends");
        movieAppRepository.save(movies1);
        System.out.println(movies1);


        //delete
        movieAppRepository.deleteById(2);
        movieAppRepository.delete(movies1);
        movieAppRepository.deleteAll();
//        movieAppRepository.deleteAllById(List.of(1, 2, 3));
    }

    @Test
    void test_pagination() {
        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id").descending());
        Page<Movies> page = movieAppRepository.findByStatus(true, pageRequest);

        System.out.println("Total pages: " + page.getTotalPages());
        page.getContent().forEach(movies -> System.out.println(movies.getName()));
    }

}
