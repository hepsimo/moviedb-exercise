package ch.dev.exercise.moviedb.repository;

import ch.dev.exercise.moviedb.domain.Movie;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
    Optional<Movie> getByMovieId(Long id);
}
