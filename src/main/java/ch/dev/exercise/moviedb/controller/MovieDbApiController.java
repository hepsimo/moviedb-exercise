package ch.dev.exercise.moviedb.controller;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface MovieDbApiController {
    @GetMapping(value = "/movies", produces = "application/json")
    ResponseEntity<List<Movie>> findAllMovies();

    @GetMapping(value = "/top-user", produces = "application/json")
    ResponseEntity<TotalCommentsPerUser> findTopUser();

    @GetMapping(value = "/top-movie", produces = "application/json")
    ResponseEntity<TotalLikePerMovie> findTopMovie();

}
