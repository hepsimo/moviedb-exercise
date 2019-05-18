package ch.dev.exercise.moviedb.config;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.service.MovieService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    static class MoviesWrapper {
        List<Movie> movies;

        @JsonCreator
        public MoviesWrapper(@JsonProperty("movies") final List<Movie> movies) {
            this.movies = movies;
        }

        public List<Movie> getMovies() {
            return movies;
        }

    }

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }

    @Bean
    CommandLineRunner dataInit(MovieService movieService, ObjectMapper jsonMapper) {
        return args -> {
            TypeReference<MoviesWrapper> typeRef = new TypeReference<MoviesWrapper>() {
            };
            InputStream input = TypeReference.class.getResourceAsStream("/json/movies.json");

            try {
                List<Movie> movies = ((MoviesWrapper) jsonMapper.readValue(input, typeRef)).getMovies();

                movieService.save(movies);
                logger.info("{} movies loaded into database", movies.size());
            }
            catch (IOException e) {
                logger.error("Failed to import movies from 'movies.json' into database", e);
                throw e;
            }
        };
    }

}
