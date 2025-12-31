package com.lms.service;

import com.lms.dao.QuestionDao;
import com.lms.dao.QuizDao;
import com.lms.dao.jdbc.JdbcQuestionDao;
import com.lms.dao.jdbc.JdbcQuizDao;
import com.lms.model.Question;
import com.lms.model.Quiz;

import java.util.List;

/**
 * Service layer for Quiz operations.
 */
public class QuizService {

    private final QuizDao quizDao = new JdbcQuizDao();
    private final QuestionDao questionDao = new JdbcQuestionDao();

    // Create quiz (Instructor)
    public Quiz createQuiz(Quiz quiz) {
        Long quizId = quizDao.createQuiz(quiz);
        quiz.setId(quizId);
        return quiz;
    }

    // Get quizzes of a course (Student)
    public List<Quiz> getQuizzesByCourse(Long courseId) {
        return quizDao.findByCourseId(courseId);
    }

    // Get questions of a quiz
    public List<Question> getQuestions(Long quizId) {
        return questionDao.findByQuizId(quizId);
    }

    // Auto-evaluate quiz
    public int evaluateQuiz(Long quizId, List<Character> answers) {
        List<Question> questions = questionDao.findByQuizId(quizId);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectOption() == answers.get(i)) {
                score++;
            }
        }
        return score;
    }
}
