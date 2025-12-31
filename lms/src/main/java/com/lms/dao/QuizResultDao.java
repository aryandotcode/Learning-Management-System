package com.lms.dao;

import com.lms.model.QuizResult;
import java.util.List;

public interface QuizResultDao {

    QuizResult save(QuizResult result);

    List<QuizResult> findByQuizId(Long quizId);

    QuizResult findByQuizAndStudent(Long quizId, Long studentId); // ✅ NEW
}
