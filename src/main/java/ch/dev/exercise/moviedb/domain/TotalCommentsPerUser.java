package ch.dev.exercise.moviedb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TotalCommentsPerUser {
    @JsonProperty("totalComments")
    private Long totalComments;
    @JsonProperty("user")
    private String user;
}
