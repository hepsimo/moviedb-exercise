package ch.dev.exercise.moviedb.repository;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;

public interface MovieRepositoryCustom {
    public TotalCommentsPerUser findTopUserByComments();

    public TotalLikePerMovie findTopMovieByLike();
}
