package ch.dev.exercise.moviedb.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryImplTest {
    private MovieRepositoryCustom testRepository;

    @Mock
    private MongoTemplate template;

    @Before
    public void setUp() {
        testRepository = new MovieRepositoryImpl(template);
    }

    @Test
    public void findTopUserByComments() {
        final TotalCommentsPerUser total = new TotalCommentsPerUser(1000L, "superFan");
        final List<TotalCommentsPerUser> topUsers = Collections.singletonList(total);
        AggregationResults<TotalCommentsPerUser> expected =
            new AggregationResults<>(topUsers, new Document());

        when(template
            .aggregate(any(Aggregation.class), eq("movies"), eq(TotalCommentsPerUser.class)))
            .thenReturn(expected);

        final TotalCommentsPerUser topUser = testRepository.findTopUserByComments();

        assertThat(topUser, is(equalTo(total)));
    }

    @Test
    public void findTopMovieByLike() {
        final TotalLikePerMovie total = new TotalLikePerMovie(1000L, "Jaws");
        final List<TotalLikePerMovie> topMovies = Collections.singletonList(total);
        AggregationResults<TotalLikePerMovie> expected =
            new AggregationResults<>(topMovies, new Document());

        when(template
            .aggregate(any(Aggregation.class), eq("movies"), eq(TotalLikePerMovie.class)))
            .thenReturn(expected);

        final TotalLikePerMovie topMovie = testRepository.findTopMovieByLike();

        assertThat(topMovie, is(equalTo(total)));
    }
}