package me.vincent.spring.tutorial.viewmodel;

import me.vincent.spring.tutorial.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 加注释
 *
 * @description: 科目学生统计信息
 * @author: Vincent Huang
 * @version:
 */

@Data
@AllArgsConstructor
public class SubjectScoresStatics {
    private String subjectName;

    private List<Score> scores;

    private double avgScore;

    private double maxScore;

    private double minScore;
}
