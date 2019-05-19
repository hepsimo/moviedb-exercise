package ch.dev.exercise.moviedb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.Comment;
import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

@RunWith(MockitoJUnitRunner.class)
public class MovieDbWebControllerImplTest {
    private MovieDbWebController testController;

    @Mock
    private Model model;
    @Mock
    private MovieService movieService;

    @Before
    public void setUp() {
        testController = new MovieDbWebControllerImpl(movieService);
    }

    @Test
    public void indexPage() {
        String uri = testController.indexPage();

        assertThat(uri, is("index"));
    }

    @Test
    public void moviesPage() {
        final List<Movie> list = Collections.singletonList(new Movie());

        when(movieService.list()).thenReturn(list);

        String uri = testController.moviesPage(model);

        assertThat(uri, is("movies"));
        verify(model).addAttribute("movies", list);
    }

    @Test
    public void commentsPage() {
        final List<Comment> comments = Collections.singletonList(new Comment());
        final Movie movie =
            new Movie(3L, "title", "description", "producer", false, "15", 0L, comments);

        when(movieService.findById(3L)).thenReturn(Optional.of(movie));

        String uri = testController.commentsPage(3L, model);

        assertThat(uri, is("comments"));
        verify(model).addAttribute("movie", movie);
    }

    @Test(expected = ResponseStatusException.class)
    public void commentsPageWhenNotFound() {
        when(movieService.findById(3L)).thenReturn(Optional.empty());

        try {
            testController.commentsPage(3L, model);
        }
        catch (ResponseStatusException e) {
            assertThat(e.getStatus(), is(HttpStatus.NOT_FOUND));
            throw e;
        }

    }

    @Test
    public void topUserPage() {
        final TotalCommentsPerUser expected = new TotalCommentsPerUser();

        when(movieService.findTopUser()).thenReturn(expected);

        String uri = testController.topUserPage(model);

        assertThat(uri, is("top-user"));
        verify(model).addAttribute("topUser", expected);
    }

    @Test
    public void topMoviePage() {
        final TotalLikePerMovie expected = new TotalLikePerMovie();

        when(movieService.findTopMovie()).thenReturn(expected);

        String uri = testController.topMoviePage(model);

        assertThat(uri, is("top-movie"));
        verify(model).addAttribute("topMovie", expected);
    }


}