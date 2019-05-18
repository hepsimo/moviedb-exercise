package ch.dev.exercise.moviedb.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {
    private Movie testMovie;
    private List<Comment> comments;

    @Before
    public void setUp() {
        comments = Arrays.asList(new Comment("user1", "Loved it", 1L, 10),
            new Comment("hater225", "Hated it", 2L, 1));
        testMovie = new Movie(1L, "", "", "", false, "PG", 1, comments);
    }

    @Test
    public void setMovieId() {
        testMovie.setMovieId(70L);
        assertThat(testMovie.getMovieId(), is(70L));
    }

    @Test
    public void setTitle() {
        testMovie.setTitle("expected");
        assertThat(testMovie.getTitle(), is("expected"));
    }

    @Test
    public void setDescription() {
        testMovie.setDescription("expected");
        assertThat(testMovie.getDescription(), is("expected"));

    }

    @Test
    public void setProducer() {
        testMovie.setProducer("expected");
        assertThat(testMovie.getProducer(), is("expected"));
    }

    @Test
    public void setAvailableIn3d() {
        testMovie.setAvailableIn3d(true);
        assertThat(testMovie.isAvailableIn3d(), is(true));
    }

    @Test
    public void setAgeRating() {
        testMovie.setAgeRating("expected");
        assertThat(testMovie.getAgeRating(), is("expected"));
    }

    @Test
    public void setLikes() {
        testMovie.setLikes(70L);
        assertThat(testMovie.getLikes(), is(70L));
    }

    @Test
    public void setComments() {
        final List<Comment> expected = Arrays.asList(new Comment());
        testMovie.setComments(expected);
        assertThat(testMovie.getComments(), is(expected));
    }

    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(Movie.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void toStringTest() {
        assertThat(testMovie.toString(),
            is("Movie(movieId=1, title=, description=, producer=, availableIn3d=false, ageRating=PG, likes=1,"
               + " comments=["
               + "Comment(user=user1, message=Loved it, dateCreated=1, like=10),"
               + " Comment(user=hater225, message=Hated it, dateCreated=2, like=1)"
               + "])"));
    }
}