package ch.dev.exercise.moviedb.domain;

import static org.hamcrest.CoreMatchers.is;
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
public class TotalCommentsPerUserTest {
    private TotalCommentsPerUser testTotal;

    @Before
    public void setUp() {
        testTotal = new TotalCommentsPerUser(100L, "movieBuff3000");
    }

    @Test
    public void setTotalComments() {
        testTotal.setTotalComments(70L);
        assertThat(testTotal.getTotalComments(), is(70L));
    }

    @Test
    public void setUser() {
        testTotal.setUser("user");
        assertThat(testTotal.getUser(), is("user"));
    }

    @Test
    public void equalsAndHashcode() {
        EqualsVerifier.forClass(TotalCommentsPerUser.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void toString1() {
        assertThat(testTotal.toString(), is("TotalCommentsPerUser(totalComments=100, user=movieBuff3000)"));
    }

    @Test
    public void jsonRepresentation() throws JsonProcessingException {
        assertThat(new ObjectMapper().writeValueAsString(testTotal),
            stringContainsInOrder("totalComments", "user"));
    }
}