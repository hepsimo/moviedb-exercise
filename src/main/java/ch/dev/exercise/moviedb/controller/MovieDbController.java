package ch.dev.exercise.moviedb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MovieDbController {
    @GetMapping("/")
    String indexPage();

    @GetMapping("/movies")
    String moviesPage(Model model);

    @GetMapping("/movie/{id}/comments")
    String commentsPage(@PathVariable("id") Long id, Model model);
}
