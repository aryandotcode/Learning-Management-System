package com.lms.service;

import com.lms.dao.QuizResultDao;
import com.lms.dao.jdbc.JdbcQuizResultDao;
import com.lms.model.QuizResult;

import java.util.List;

public class QuizResultService {

    private final QuizResultDao dao = new JdbcQuizResultDao();

    public void saveResult(Long quizId, Long studentId, int score) {
        dao.save(new QuizResult(quizId, studentId, score));
    }

    public List<QuizResult> getResultsByQuiz(Long quizId) {
        return dao.findByQuizId(quizId);
    }

    // ✅ Student specific result
    public QuizResult getResultByQuizAndStudent(Long quizId, Long studentId) {
        return dao.findByQuizAndStudent(quizId, studentId);
    }
}
