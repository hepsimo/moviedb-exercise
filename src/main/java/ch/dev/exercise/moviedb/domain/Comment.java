package ch.dev.exercise.moviedb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Comment {
    @JsonProperty("user")
    private String user;
    @JsonProperty("message")
    private String message;
    @JsonProperty("dateCreated")
    private long dateCreated;
    @JsonProperty("like")
    private int like;

    @JsonProperty("createdAt")
    @JsonFormat(pattern = "dd MMM yyyy, HH:mm:ss", timezone = "UTC")
    public Instant getCreatedAt() {
        return Instant.ofEpochSecond(dateCreated);
    }
}
