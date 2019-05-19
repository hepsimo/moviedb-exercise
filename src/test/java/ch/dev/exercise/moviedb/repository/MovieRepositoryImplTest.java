package ch.dev.exercise.moviedb.repository;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
        //when(template).

    }
}