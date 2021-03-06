package ch.dev.exercise.moviedb.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import ch.dev.exercise.moviedb.MoviedbApplication;
import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import ch.dev.exercise.moviedb.service.MovieService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MoviedbApplication.class})
public class MovieRepositoryImplIT {
    private static Logger logger = LoggerFactory.getLogger(MovieRepositoryImplIT.class);
    private static final String COLLECTION_NAME = "movies";

    private MovieRepositoryCustom testRepository;

    private Movie movie;

    @Autowired
    private MongoTemplate template;
    @Autowired
    private MovieService movieService;

    @Test
    public void checkMongoTemplate() {
        // actual data was loaded by Config.dataInit().run()
        assertThat(template, is(notNullValue()));
        assertThat(template.collectionExists(COLLECTION_NAME), is(true));
    }


    @Test
    public void findTopUserByComments() {
        final TotalCommentsPerUser topUser = movieService.findTopUser();

        assertThat(topUser, is(new TotalCommentsPerUser(4L, "testingPriest")));
    }

    @Test
    public void findTopMovieByLike() {
        final TotalLikePerMovie topMovie = movieService.findTopMovie();

        assertThat(topMovie, is(new TotalLikePerMovie(25L, "Jaws")));
    }
}