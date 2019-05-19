package ch.dev.exercise.moviedb.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sortByCount;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import ch.dev.exercise.moviedb.domain.TotalCommentsPerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortByCountOperation;

public class MovieRepositoryImpl implements MovieRepositoryCustom {
    private MongoTemplate template;

    @Autowired
    public MovieRepositoryImpl(final MongoTemplate template) {
        this.template = template;
    }

    @Override
    public Iterable<TotalCommentsPerUser> findTopUsersByComments() {
        SortByCountOperation sortByUser = sortByCount("comments.user");
        Aggregation aggregation = Aggregation
            .newAggregation(unwind("comments"), sortByCount("comments.user"),
                project().and("_id").as("user").and("count").as("totalComments"));

        AggregationResults<TotalCommentsPerUser> results =
            template.aggregate(aggregation, "movies", TotalCommentsPerUser.class);

        return results.getMappedResults();
    }
}
