package ch.dev.exercise.moviedb.domain;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentTest {
    private Comment testComment;

    @Before
    public void setUp() {
        testComment = new Comment("snarky1", "Made by eyes hurt", 123_456_789L, 0);
    }

    @Test
    public void setUserName() {
        testComment.setUser("expected");
        assertThat(testComment.getUser(), is("expected"));
    }

    @Test
    public void setMessage() {
        testComment.setMessage("expected");
        assertThat(testComment.getMessage(), is("expected"));

    }

    @Test
    public void setDateCreated() {
        testComment.setDateCreated(123_456_789L);
        assertThat(testComment.getDateCreated(), is(123_456_789L));
    }

    @Test
    public void getCreatedAt() {
        Instant expected = Instant.ofEpochSecond(3000L);

        testComment.setDateCreated(3000L);

        assertThat(testComment.getCreatedAt(), is(expected));
    }

    @Test
    public void setLike() {
        testComment.setLike(10);
        assertThat(testComment.getLike(), is(10));
    }

    @Test
    public void toStringTest() {
        assertThat(testComment.toString(),
            is("Comment(user=snarky1, message=Made by eyes hurt, dateCreated=123456789, like=0)"));
    }

    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(Comment.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void jsonRepresentation() throws JsonProcessingException {
        final String json = new ObjectMapper().writeValueAsString(testComment);
        assertThat(json, stringContainsInOrder("user", "message", "dateCreated", "like"));
        assertThat(json, not(containsString("createdAt")));
    }

}