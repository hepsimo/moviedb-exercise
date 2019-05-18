package ch.dev.exercise.moviedb.controller;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MovieDbControllerImpl implements MovieDbController {
    private MovieService movieService;

    @Autowired
    public MovieDbControllerImpl(final MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @Override
    @GetMapping("/movies")
    public String moviesPage(Model model) {
        model.addAttribute("movies", movieService.list());

        return "movies";
    }

    @Override
    @GetMapping("/movie/{id}/comments")
    public String commentsPage(@PathVariable("id") Long id, Model model) {
        final Optional<Movie> movie = movieService.findById(id);

        if (!movie.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        model.addAttribute("movie", movie.get());

        return "comments";
    }
}
