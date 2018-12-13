package me.vincent.spring.tutorial.repositoroy.mongodb;

import me.vincent.spring.tutorial.domain.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {

    List<Subject> findBySubjectName(String subjectName);

    void deleteBySubjectName(String subjectName);

}
