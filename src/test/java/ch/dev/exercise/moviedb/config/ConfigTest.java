package ch.dev.exercise.moviedb.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.CommandLineRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigTest {
    private Config testConfig;

    @Mock
    private MovieService movieService;
    @Mock
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        testConfig = new Config();
    }

    @Test
    public void dataInit() throws Exception {
        final CommandLineRunner runner = testConfig.dataInit(movieService, mapper);
        final List<Movie> movies = Collections.singletonList(new Movie());
        final Config.MoviesWrapper expected = new Config.MoviesWrapper(movies);

        when(mapper.readValue(any(InputStream.class), any(TypeReference.class)))
            .thenReturn(expected);

        runner.run();

        verify(movieService).save(movies);
    }

    @Test(expected = IOException.class)
    public void dataInitWhenNotFound() throws Exception {
        final CommandLineRunner runner = testConfig.dataInit(movieService, mapper);

        when(mapper.readValue(any(InputStream.class), any(TypeReference.class)))
            .thenThrow(new IOException("not found"));

        runner.run();
    }
}