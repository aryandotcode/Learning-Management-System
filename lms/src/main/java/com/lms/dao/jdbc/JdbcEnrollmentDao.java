package com.lms.dao.jdbc;

import com.lms.dao.EnrollmentDao;
import com.lms.model.Enrollment;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEnrollmentDao implements EnrollmentDao {

    @Override
    public synchronized Enrollment save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, enrollment.getStudentId());
            ps.setLong(2, enrollment.getCourseId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    enrollment.setId(rs.getLong(1));
                }
            }
            return enrollment;

        } catch (SQLException ex) {
            if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("duplicate")) {
                throw new RuntimeException("Student already enrolled in this course");
            }
            throw new RuntimeException("Error saving enrollment: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        String sql = "UPDATE enrollments SET student_id=?, course_id=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, enrollment.getStudentId());
            ps.setLong(2, enrollment.getCourseId());
            ps.setLong(3, enrollment.getId());
            ps.executeUpdate();
            return enrollment;

        } catch (SQLException ex) {
            throw new RuntimeException("Error updating enrollment: " + ex.getMessage(), ex);
        }
    }


    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM enrollments WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error deleting enrollment: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        String sql = "SELECT id, student_id, course_id FROM enrollments WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException ex) {
            throw new RuntimeException("Error finding enrollment by id: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Enrollment> findAll() {
        String sql = "SELECT id, student_id, course_id FROM enrollments";
        List<Enrollment> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException("Error listing enrollments: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Enrollment> findByStudentAndCourse(Long studentId, Long courseId) {
        String sql = "SELECT id, student_id, course_id FROM enrollments WHERE student_id=? AND course_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, studentId);
            ps.setLong(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException ex) {
            throw new RuntimeException("Error finding enrollment: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        String sql = "SELECT id, student_id, course_id FROM enrollments WHERE student_id=?";
        List<Enrollment> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException("Error listing enrollments: " + ex.getMessage(), ex);
        }
    }

    private Enrollment mapRow(ResultSet rs) throws SQLException {
        Enrollment e = new Enrollment();
        e.setId(rs.getLong("id"));
        e.setStudentId(rs.getLong("student_id"));
        e.setCourseId(rs.getLong("course_id"));
        return e;
    }
}
