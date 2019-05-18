package ch.dev.exercise.moviedb.service;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.repository.MovieRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository repo;

    public MovieService(final MovieRepository movieRepository) {
        this.repo = movieRepository;
    }

    public Iterable<Movie> list() {
        return repo.findAll();
    }

    public Iterable<Movie> save(List<Movie> movies) {
        return repo.saveAll(movies);
    }
}
