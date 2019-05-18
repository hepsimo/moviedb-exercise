package ch.dev.exercise.moviedb.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor()
public final class Movie {
    @Id
    @JsonProperty("movie_id")
    private long movieId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("producer")
    private String producer;
    @JsonProperty("available_in_3d")
    private boolean availableIn3d;
    @JsonProperty("age_rating")
    private String ageRating;
    @JsonProperty("likes")
    private long likes;
    @JsonProperty("comments")
    private List<Comment> comments;
}

