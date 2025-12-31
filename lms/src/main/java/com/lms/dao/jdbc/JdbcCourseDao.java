package com.lms.dao.jdbc;

import com.lms.dao.CourseDao;
import com.lms.model.Course;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple JDBC implementation for CourseDao.
 * Write methods are synchronized to avoid race conditions in demo.
 */
public class JdbcCourseDao implements CourseDao {

    @Override
    public synchronized Course save(Course course) {
        String sql = "INSERT INTO courses (title, description, instructor_id) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getDescription());
            if (course.getInstructorId() == null) {
                ps.setNull(3, Types.BIGINT);
            } else {
                ps.setLong(3, course.getInstructorId());
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) course.setId(rs.getLong(1));
            }
            return course;
        } catch (SQLException ex) {
            throw new RuntimeException("Error saving course: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Course update(Course course) {
        String sql = "UPDATE courses SET title=?, description=?, instructor_id=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getDescription());
            if (course.getInstructorId() == null) ps.setNull(3, Types.BIGINT);
            else ps.setLong(3, course.getInstructorId());
            ps.setLong(4, course.getId());
            ps.executeUpdate();
            return course;
        } catch (SQLException ex) {
            throw new RuntimeException("Error updating course: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM courses WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error deleting course: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        String sql = "SELECT id,title,description,instructor_id FROM courses WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Error finding course by id: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT id,title,description,instructor_id FROM courses";
        List<Course> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Error listing courses: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Course> findByInstructorId(Long instructorId) {
        String sql = "SELECT id,title,description,instructor_id FROM courses WHERE instructor_id=?";
        List<Course> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, instructorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Error listing courses by instructor: " + ex.getMessage(), ex);
        }
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        Course c = new Course();
        c.setId(rs.getLong("id"));
        c.setTitle(rs.getString("title"));
        c.setDescription(rs.getString("description"));
        long instr = rs.getLong("instructor_id");
        if (rs.wasNull()) c.setInstructorId(null);
        else c.setInstructorId(instr);
        return c;
    }
}
