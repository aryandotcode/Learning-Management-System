package com.lms.dao.jdbc;

import com.lms.dao.QuizDao;
import com.lms.model.Quiz;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuizDao implements QuizDao {

    @Override
    public Long createQuiz(Quiz quiz) {
        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO quizzes(course_id, title) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, quiz.getCourseId());
            ps.setString(2, quiz.getTitle());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error creating quiz", e);
        }
        return null;
    }

    @Override
    public List<Quiz> findByCourseId(Long courseId) {

        List<Quiz> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM quizzes WHERE course_id=?"
            );
            ps.setLong(1, courseId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getLong("id"));
                q.setCourseId(rs.getLong("course_id"));
                q.setTitle(rs.getString("title"));
                list.add(q);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching quizzes", e);
        }

        return list;
    }
}
