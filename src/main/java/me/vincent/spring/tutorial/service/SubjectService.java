package me.vincent.spring.tutorial.service;

import me.vincent.spring.tutorial.domain.Subject;
import me.vincent.spring.tutorial.repositoroy.mongodb.ScoresRepository;
import me.vincent.spring.tutorial.repositoroy.mongodb.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加注释
 *
 * @description: 科目服务
 * @author: Vincent Huang
 * @version:
 */

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ScoresRepository scoresRepository;

    public List<Subject> findAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject addSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public void deleteSubjectByName(String subjectName){
        subjectRepository.deleteBySubjectName(subjectName);
        scoresRepository.deleteBySubjectName(subjectName);
    }
}
