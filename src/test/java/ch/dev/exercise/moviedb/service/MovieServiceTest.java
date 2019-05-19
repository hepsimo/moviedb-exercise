package ch.dev.exercise.moviedb.service;

import static org.mockito.Mockito.verify;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.repository.MovieRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    private MovieService testService;

    @Mock
    private MovieRepository repository;

    @Before
    public void setUp() {
        testService = new MovieServiceImpl(repository);
    }

    @Test
    public void list() {
        testService.list();

        verify(repository).findAll();
    }

    @Test
    public void save() {
        final List<Movie> movies = Arrays.asList(new Movie());

        testService.save(movies);

        verify(repository).saveAll(movies);
    }

    @Test
    public void getById() {
        testService.findById(3L);

        verify(repository).getByMovieId(3L);
    }

    @Test
    public void findtopUser() {
        testService.findTopUser();

        verify(repository).findTopUserByComments();
    }
}