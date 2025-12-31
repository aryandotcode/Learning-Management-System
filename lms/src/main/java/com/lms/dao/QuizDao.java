package com.lms.dao;

import com.lms.model.Quiz;
import java.util.List;

public interface QuizDao {

    Long createQuiz(Quiz quiz);

    List<Quiz> findByCourseId(Long courseId);
}
