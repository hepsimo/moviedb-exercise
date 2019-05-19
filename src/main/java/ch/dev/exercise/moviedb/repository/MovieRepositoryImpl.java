package ch.dev.exercise.moviedb.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sortByCount;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import ch.dev.exercise.moviedb.domain.TotalLikePerMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

public class MovieRepositoryImpl implements MovieRepositoryCustom {
    private MongoTemplate template;

    @Autowired
    public MovieRepositoryImpl(final MongoTemplate template) {
        this.template = template;
    }

    @Override
    public TotalCommentsPerUser findTopUserByComments() {
        Aggregation aggregation = Aggregation
            .newAggregation(unwind("comments"), sortByCount("comments.user"),
                project().and("_id").as("user").and("count").as("totalComments"), limit(1));

        AggregationResults<TotalCommentsPerUser> results =
            template.aggregate(aggregation, "movies", TotalCommentsPerUser.class);

        return results.getUniqueMappedResult();
    }

    @Override
    public TotalLikePerMovie findTopMovieByLike() {
        Aggregation aggregation = Aggregation
            .newAggregation(unwind("comments"), group("title").sum("$comments.like").as("totalLike"),
                project().and("_id").as("title").and("totalLike").as("totalLike"), sort(Sort.Direction.DESC, "totalLike"),
                limit(1));

        AggregationResults<TotalLikePerMovie> results =
            template.aggregate(aggregation, "movies", TotalLikePerMovie.class);

        return results.getUniqueMappedResult();
    }
}
