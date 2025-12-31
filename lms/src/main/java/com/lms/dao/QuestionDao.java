package com.lms.dao;

import com.lms.model.Question;

import java.util.List;

public interface QuestionDao {
    Question save(Question question);
    List<Question> findByQuizId(Long quizId);
}
