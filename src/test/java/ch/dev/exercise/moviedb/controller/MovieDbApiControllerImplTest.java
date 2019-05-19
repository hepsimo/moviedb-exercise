package ch.dev.exercise.moviedb.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class MovieDbApiControllerImplTest {
    private MovieDbApiController testController;

    @Mock
    private MovieService movieService;

    @Before
    public void setUp() {
        testController = new MovieDbApiControllerImpl(movieService);
    }

    @Test
    public void findAllMovies() {
        final List<Movie> movies = Collections
            .singletonList(new Movie(1L, "", "", "", false, "PG", 1, Collections.emptyList()));

        when(movieService.list()).thenReturn(movies);

        final ResponseEntity<List<Movie>> response = testController.findAllMovies();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(movies));
    }

    @Test
    public void findTopUser() {
        final TotalCommentsPerUser topUser = new TotalCommentsPerUser(100L, "movieGoer");

        when(movieService.findTopUser()).thenReturn(topUser);

        final ResponseEntity<TotalCommentsPerUser> response = testController.findTopUser();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(topUser));
    }

    @Test
    public void findTopMovie() {
        final TotalLikePerMovie topMovie = new TotalLikePerMovie(100L, "Jaws");

        when(movieService.findTopMovie()).thenReturn(topMovie);

        final ResponseEntity<TotalLikePerMovie> response = testController.findTopMovie();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(topMovie));
    }
}