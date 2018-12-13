package me.vincent.spring.tutorial.repositoroy.mongodb;

import me.vincent.spring.tutorial.domain.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoresRepository extends MongoRepository<Score, String> {

    @Query(value = "{'student.name' : ?0 }", sort = "{ 'subject.subjectName' : 1}")
    List<Score> findScoresByStudentNameSortBySubjectName(String name);

    @Query(value = "{'subject.subjectName' : ?0 }", sort = "{ 'score' : -1}")
    List<Score> findScoresBySubjectNameSortByScoreDesc(String name);

    @Override
    <S extends Score> List<S> saveAll(Iterable<S> entities);

    @Query(value = "{'subject.subjectName' : ?0}", delete = true)
    void deleteBySubjectName(String s);
}
