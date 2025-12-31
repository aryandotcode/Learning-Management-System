package com.lms.service;

import com.lms.dao.QuestionDao;
import com.lms.dao.jdbc.JdbcQuestionDao;
import com.lms.model.Question;

import java.util.List;

/**
 * Service layer for Question operations.
 */
public class QuestionService {

    private final QuestionDao questionDao = new JdbcQuestionDao();

    /**
     * Add a question to a quiz
     */
    public Question addQuestion(Question question) {
        // 🔥 return the saved question with generated ID
        return questionDao.save(question);
    }

    /**
     * Get all questions of a quiz
     */
    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionDao.findByQuizId(quizId);
    }
}
