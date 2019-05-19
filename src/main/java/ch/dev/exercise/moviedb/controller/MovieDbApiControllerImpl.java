package ch.dev.exercise.moviedb.controller;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api")
public class MovieDbApiControllerImpl implements MovieDbApiController {
    private MovieService movieService;

    @Autowired
    public MovieDbApiControllerImpl(final MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    @GetMapping(value = "/movies", produces = "application/json")
    public ResponseEntity<List<Movie>> findAllMovies() {
        final Iterable<Movie> movies = movieService.list();

        return ResponseEntity
            .ok(StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    @GetMapping(value = "/top-user", produces = "application/json")
    public ResponseEntity<TotalCommentsPerUser> findTopUser() {
        final TotalCommentsPerUser topUser = movieService.findTopUser();

        return ResponseEntity.ok(topUser);
    }

    @Override
    @GetMapping(value = "/top-movie", produces = "application/json")
    public ResponseEntity<TotalLikePerMovie> findTopMovie() {
        final TotalLikePerMovie topMovie = movieService.findTopMovie();

        return ResponseEntity.ok(topMovie);
    }
}
