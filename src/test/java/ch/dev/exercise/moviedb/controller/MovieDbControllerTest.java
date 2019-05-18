package ch.dev.exercise.moviedb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
public class MovieDbControllerTest {
    private MovieDbController testController;

    @Mock
    private Model model;
    @Mock
    private MovieService movieService;

    @Before
    public void setUp() {
        testController = new MovieDbController(movieService);
    }

    @Test
    public void indexPage() {
        final List<Movie> list = Arrays.asList(new Movie());

        when(movieService.list()).thenReturn(list);

        String uri = testController.indexPage(model);

        assertThat(uri, is("index"));
        verify(model).addAttribute("movies", list);
    }
}