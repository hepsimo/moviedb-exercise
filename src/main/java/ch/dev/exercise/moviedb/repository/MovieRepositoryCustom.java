package ch.dev.exercise.moviedb.repository;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import java.util.List;

public interface MovieRepositoryCustom {
    public List<TotalCommentsPerUser> findTopUsersByComments();
}
