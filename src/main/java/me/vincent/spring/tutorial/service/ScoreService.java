package me.vincent.spring.tutorial.service;

import me.vincent.spring.tutorial.viewmodel.SubjectScoresStatics;
import me.vincent.spring.tutorial.domain.Score;
import me.vincent.spring.tutorial.repositoroy.mongodb.CustomedScoresRepository;
import me.vincent.spring.tutorial.repositoroy.mongodb.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加注释
 *
 * @description: 分数查询服务
 * @author: Vincent Huang
 * @version:
 */
@Service
public class ScoreService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private CustomedScoresRepository customedScoresRepository;

    public List<Score> findScoreByStudentName(String studentName){
        return scoresRepository.findScoresByStudentNameSortBySubjectName(studentName);
    }

    public SubjectScoresStatics findSubjectScoresStatics(String subjectName){
        List<Score> scores = scoresRepository.findScoresBySubjectNameSortByScoreDesc(subjectName);
        double maxScore = customedScoresRepository.calculateMaxScoreBySubject(subjectName);
        double minScore = customedScoresRepository.calculateMinScoreBySubject(subjectName);
        double avgScore = customedScoresRepository.calculateAverageScoreBySubject(subjectName);

        return new SubjectScoresStatics(subjectName, scores, avgScore, maxScore, minScore);
    }

    public List<Score> saveScores(List<Score> scores){
        List<Score> scoresSaved = scoresRepository.saveAll(scores);
        return scoresSaved;
    }
}
