package me.vincent.spring.tutorial.repositoroy.mongodb;

import me.vincent.spring.tutorial.domain.Score;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


/**
 * @description: 换一种方式实现复杂查询的Repository
 * @auth wshuang
 * @version 0.0.1
 */

@Repository
public class CustomedScoresRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Score> findScoresBySubjectNameSortByScoreDesc(String subjectName){
        List<Score> scores = mongoTemplate.find(query(where("subject.subjectName").is(subjectName)).with(Sort.by(Sort.Order.desc("score"))), Score.class);
        return scores;
    }

    public double calculateAverageScoreBySubject(String subjectName){
//        Document match = Document.parse("{ $match : { \"subject.subjectName\" : \"English\"}}");
//        Document group = Document.parse("{$group : { \"_id\" : \"$subject\", \"avg_score\": { $avg : \"$score\" }} }");
//        Aggregates.match(new Document());
//        AggregateIterable<Document> aggregateIterable = mongoTemplate.getCollection("score").aggregate(Arrays.asList(match, group));

        Aggregation agg = newAggregation(
                match(where("subject.subjectName").is(subjectName)),
                group("subject").avg("score").as("avg_score"));
        List<Document> results = mongoTemplate.aggregate(agg, Score.class, Document.class).getMappedResults();

        return (double)results.get(0).get("avg_score");
    }

    public double calculateMaxScoreBySubject(String subjectName){
//        Document match = Document.parse("{ $match : { \"subject.subjectName\" : \"English\"}}");
//        Document group = Document.parse("{$group : { \"_id\" : \"$subject\", \"max_score\": { $max : \"$score\" }} }");
//        Aggregates.match(new Document());
//        Document result = mongoTemplate.getCollection("score").aggregate(Arrays.asList(match, group)).first();

        Aggregation agg = newAggregation(
                match(where("subject.subjectName").is(subjectName)),
                group("subject").max("score").as("max_score"));
        List<Document> results = mongoTemplate.aggregate(agg, Score.class, Document.class).getMappedResults();
        Document result = results.get(0);
        return (Double)result.get("max_score");
    }

    public double calculateMinScoreBySubject(String subjectName){
//        Document match = Document.parse("{ $match : { \"subject.subjectName\" : \"English\"}}");
//        Document group = Document.parse("{$group : { \"_id\" : \"$subject\", \"max_score\": { $max : \"$score\" }} }");
//        Aggregates.match(new Document());
//        Document result = mongoTemplate.getCollection("score").aggregate(Arrays.asList(match, group)).first();

        Aggregation agg = newAggregation(
                match(where("subject.subjectName").is(subjectName)),
                group("subject").min("score").as("min_score"));
        List<Document> results = mongoTemplate.aggregate(agg, Score.class, Document.class).getMappedResults();
        Document result = results.get(0);
        return (Double)result.get("min_score");
    }

}
