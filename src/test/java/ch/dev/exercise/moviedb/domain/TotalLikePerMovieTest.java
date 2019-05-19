package ch.dev.exercise.moviedb.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TotalLikePerMovieTest {
    private TotalLikePerMovie testTotal;

    @Before
    public void setUp() {
        testTotal = new TotalLikePerMovie(100L, "Jaws");
    }

    @Test
    public void setTotalComments() {
        testTotal.setTotalLike(70L);
        assertThat(testTotal.getTotalLike(), is(70L));
    }

    @Test
    public void setUser() {
        testTotal.setTitle("Psycho");
        assertThat(testTotal.getTitle(), is("Psycho"));
    }

    @Test
    public void equalsAndHashcode() {
        EqualsVerifier.forClass(TotalLikePerMovie.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void toStringTest() {
        assertThat(testTotal.toString(), is("TotalLikePerMovie(totalLike=100, title=Jaws)"));
    }

    @Test
    public void jsonRepresentation() throws JsonProcessingException {
        assertThat(new ObjectMapper().writeValueAsString(testTotal),
            stringContainsInOrder("totalLike", "title"));
    }
}