package ch.dev.exercise.moviedb.controller;

import ch.dev.exercise.moviedb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieDbController {
    private MovieService movieService;

    @Autowired
    public MovieDbController(final MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("movies", movieService.list());

        return "index";
    }
}
