package ch.dev.exercise.moviedb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TotalLikePerMovie {
    @JsonProperty("totalLike")
    private Long totalLike;
    @JsonProperty("title")
    private String title;
}
