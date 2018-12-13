package me.vincent.spring.tutorial.service;

import me.vincent.spring.tutorial.domain.Student;
import me.vincent.spring.tutorial.repositoroy.mongodb.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加注释
 *
 * @description: Subject的修改服务
 * @author: Vincent Huang
 * @version:0.0.1
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(Student student){
        Student studentSaved = studentRepository.save(student);
        return studentSaved;
    }

    public void deleteStudentByName(String name){
        studentRepository.deleteByName(name);
    }

}
