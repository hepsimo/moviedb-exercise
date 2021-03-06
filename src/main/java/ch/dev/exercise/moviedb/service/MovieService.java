package ch.dev.exercise.moviedb.service;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    Iterable<Movie> list();

    Iterable<Movie> save(List<Movie> movies);

    Optional<Movie> findById(Long id);

    TotalCommentsPerUser findTopUser();

    TotalLikePerMovie findTopMovie();
}
