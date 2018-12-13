package me.vincent.spring.tutorial.repositoroy.mongodb;

import me.vincent.spring.tutorial.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    void deleteByName(String name);
}
