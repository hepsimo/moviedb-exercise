package ch.dev.exercise.moviedb.repository;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;

public interface MovieRepositoryCustom {
    public Iterable<TotalCommentsPerUser> findTopUsersByComments();
}
