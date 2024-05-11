package org.gb.movieapp;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Repository.BlogRepository;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Utils.RandomColor;
import org.gb.movieapp.entites.Blogs;
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
    @Autowired
    BlogRepository blogRepository;

    @Test
    void saveMovie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 140; i++) {
            String color = RandomColor.getRandomColor();
            String name = faker.book().title();
            Movies movie = Movies.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400/" + color + "/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase())
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
    void saveBlog() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

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
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void test_Movie_Query() {
        List<Movies> movies = movieAppRepository.findAll();
        System.out.println("Size: " + movies.size());

        //
        List<Movies> moviesById = movieAppRepository.findAllById(List.of(1, 2, 3));

        //
        //delete
        movieAppRepository.deleteById(2);
        movieAppRepository.deleteAll();
//        movieAppRepository.deleteAllById(List.of(1, 2, 3));
    }

    @Test
    void test_pagination() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Movies> page = movieAppRepository.findByStatus(true, pageRequest);

        System.out.println("Total pages: " + page.getTotalPages());
        page.getContent().forEach(movies -> System.out.println(movies.getName()));
    }

}
