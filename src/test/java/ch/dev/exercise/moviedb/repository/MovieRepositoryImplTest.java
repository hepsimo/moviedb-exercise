package ch.dev.exercise.moviedb.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
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
    public void findTopUsersByComments() {
        final List<TotalCommentsPerUser> topUserList =
            Collections.singletonList(new TotalCommentsPerUser(1000L, "superFan"));
        AggregationResults<TotalCommentsPerUser> expected =
            new AggregationResults<>(topUserList, new Document());

        when(template
            .aggregate(any(Aggregation.class), eq("movies"), eq(TotalCommentsPerUser.class)))
            .thenReturn(expected);

        final List<TotalCommentsPerUser> topUsers = testRepository.findTopUsersByComments();

        assertThat(topUsers, is(topUserList));
    }
}