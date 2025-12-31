package com.lms.dao.jdbc;

import com.lms.dao.QuestionDao;
import com.lms.model.Question;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuestionDao implements QuestionDao {

    @Override
    public Question save(Question q) {

        String sql = """
            INSERT INTO questions
            (quiz_id, question, option_a, option_b, option_c, option_d, correct_option)
            VALUES (?,?,?,?,?,?,?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, q.getQuizId());
            ps.setString(2, q.getQuestion());
            ps.setString(3, q.getOptionA());
            ps.setString(4, q.getOptionB());
            ps.setString(5, q.getOptionC());
            ps.setString(6, q.getOptionD());
            ps.setString(7, String.valueOf(q.getCorrectOption()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                q.setId(rs.getLong(1));
            }
            return q;

        } catch (Exception e) {
            throw new RuntimeException("Error saving question", e);
        }
    }

    @Override
    public List<Question> findByQuizId(Long quizId) {

        String sql = "SELECT * FROM questions WHERE quiz_id=?";
        List<Question> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, quizId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getLong("id"));
                q.setQuizId(rs.getLong("quiz_id"));
                q.setQuestion(rs.getString("question"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option").charAt(0));
                list.add(q);
            }
            return list;

        } catch (Exception e) {
            throw new RuntimeException("Error loading questions", e);
        }
    }
}
