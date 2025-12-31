package com.lms.dao.jdbc;

import com.lms.dao.QuizResultDao;
import com.lms.model.QuizResult;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuizResultDao implements QuizResultDao {

    @Override
    public QuizResult save(QuizResult r) {
        String sql = "INSERT INTO quiz_results (quiz_id, student_id, score) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, r.getQuizId());
            ps.setLong(2, r.getStudentId());
            ps.setInt(3, r.getScore());
            ps.executeUpdate();
            return r;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuizResult> findByQuizId(Long quizId) {
        List<QuizResult> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM quiz_results WHERE quiz_id=?")) {

            ps.setLong(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuizResult r = new QuizResult();
                r.setId(rs.getLong("id"));
                r.setQuizId(rs.getLong("quiz_id"));
                r.setStudentId(rs.getLong("student_id"));
                r.setScore(rs.getInt("score"));
                list.add(r);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // ✅ Student specific
    @Override
    public QuizResult findByQuizAndStudent(Long quizId, Long studentId) {
        String sql = "SELECT * FROM quiz_results WHERE quiz_id=? AND student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, quizId);
            ps.setLong(2, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                QuizResult r = new QuizResult();
                r.setId(rs.getLong("id"));
                r.setQuizId(quizId);
                r.setStudentId(studentId);
                r.setScore(rs.getInt("score"));
                return r;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
