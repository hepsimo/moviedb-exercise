package ch.dev.exercise.moviedb.service;

import ch.dev.exercise.moviedb.domain.Movie;
import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.repository.MovieRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository repo;

    public MovieServiceImpl(final MovieRepository movieRepository) {
        this.repo = movieRepository;
    }

    @Override
    public Iterable<Movie> list() {
        return repo.findAll();
    }

    @Override
    public Iterable<Movie> save(List<Movie> movies) {
        return repo.saveAll(movies);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return repo.getByMovieId(id);
    }

    @Override
    public List<TotalCommentsPerUser> findTopUsers() {
        return repo.findTopUsersByComments();
    }
}
