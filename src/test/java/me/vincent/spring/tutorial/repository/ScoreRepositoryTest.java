package me.vincent.spring.tutorial.repository;


import me.vincent.spring.tutorial.domain.Score;
import me.vincent.spring.tutorial.domain.Student;
import me.vincent.spring.tutorial.domain.Subject;
import me.vincent.spring.tutorial.repositoroy.mongodb.CustomedScoresRepository;
import me.vincent.spring.tutorial.repositoroy.mongodb.ScoresRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@Import(CustomedScoresRepository.class)
public class ScoreRepositoryTest {

    private List<Score> scores = new ArrayList<>();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CustomedScoresRepository customedScoresRepository;

    @Autowired
    private ScoresRepository scoresRepository;

    @Before
    public void prepare() {
        scoresRepository.deleteAll();

        Subject preparedSubject1 = new Subject();
        preparedSubject1.setSubjectName("Math");

        Subject preparedSubject2 = new Subject();
        preparedSubject2.setSubjectName("English");


        Student student1 = new Student();
        student1.setName("A");

        Student student2 = new Student();
        student2.setName("B");

        Student student3 = new Student();
        student3.setName("C");

        List<Student> preparedStudents = Arrays.asList(student1, student2, student3);

        Score score1 = new Score();
        score1.setScore(12.0);
        score1.setStudent(preparedStudents.get(0));
        score1.setSubject(preparedSubject1);

        Score score2 = new Score();
        score2.setScore(2.0);
        score2.setStudent(preparedStudents.get(1));
        score2.setSubject(preparedSubject1);

        Score score3 = new Score();
        score3.setScore(6.0);
        score3.setStudent(preparedStudents.get(2));
        score3.setSubject(preparedSubject1);

        Score score4 = new Score();
        score4.setScore(5.0);
        score4.setStudent(preparedStudents.get(0));
        score4.setSubject(preparedSubject2);

        Score score5 = new Score();
        score5.setScore(4.0);
        score5.setStudent(preparedStudents.get(1));
        score5.setSubject(preparedSubject2);

        Score score6 = new Score();
        score6.setScore(9.0);
        score6.setStudent(preparedStudents.get(2));
        score6.setSubject(preparedSubject2);

        scores = Arrays.asList(score1, score2, score3, score4, score5, score6);
    }

    @Test
    public void testSaveScoresRepository(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        Assert.assertEquals(6, scores.size());
    }

    @Test
    public void testQueryByStudentNameSortBySubjectName(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        List<Score> targetScores = scoresRepository.findScoresByStudentNameSortBySubjectName("A");
        Assert.assertTrue(targetScores.get(0).getSubject().getSubjectName().equals("English"));
    }

    @Test
    public void testQueryBySubjectNameSortByScore(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        List<Score> targetScores = scoresRepository.findScoresBySubjectNameSortByScoreDesc("English");
        Assert.assertTrue(targetScores.get(0).getScore() == 9);
        Assert.assertTrue(targetScores.get(1).getScore() == 5);
        Assert.assertTrue(targetScores.get(2).getScore() == 4);
    }

    @Test
    public void testQueryBySubjectNameSortByScoreWithMongoTemplate(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        List<Score> targetScores = customedScoresRepository.findScoresBySubjectNameSortByScoreDesc("English");
        Assert.assertTrue(targetScores.get(0).getScore() == 9);
        Assert.assertTrue(targetScores.get(1).getScore() == 5);
        Assert.assertTrue(targetScores.get(2).getScore() == 4);
    }

    @Test
    public void testAverageScoreOfSubject(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        double actual_avg_score = customedScoresRepository.calculateAverageScoreBySubject("English");
        Assert.assertEquals(6.0, actual_avg_score, 0.0000000001);
    }

    @Test
    public void testMaxScoreOfSubject(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        double actual_avg_score = customedScoresRepository.calculateMaxScoreBySubject("English");
        Assert.assertEquals(9.0, actual_avg_score, 0.0000000001);
    }

    @Test
    public void testMinScoreOfSubject(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        double actual_avg_score = customedScoresRepository.calculateMaxScoreBySubject("English");
        Assert.assertEquals(9.0, actual_avg_score, 0.0000000001);
    }

    @Test
    public void testDeleteSubjectsByName(){
        List<Score> savedScores = scoresRepository.saveAll(scores);
        scoresRepository.deleteBySubjectName("English");
        List<Score> shouldBeDeletedScores = scoresRepository.findScoresBySubjectNameSortByScoreDesc("English");
        Assert.assertTrue(shouldBeDeletedScores.size() == 0);
    }

    @After
    public void cleanUp(){
        scoresRepository.deleteAll();
    }
}
