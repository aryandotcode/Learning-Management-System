package com.lms.dao.jdbc;

import com.lms.dao.CourseContentDao;
import com.lms.model.CourseContent;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseContentDao implements CourseContentDao {

    @Override
    public void save(CourseContent content) {
        String sql = "INSERT INTO course_contents(course_id, file_name, file_path) VALUES (?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, content.getCourseId());
            ps.setString(2, content.getFileName());
            ps.setString(3, content.getFilePath());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving course content", e);
        }
    }

    @Override
    public List<CourseContent> findByCourseId(Long courseId) {
        List<CourseContent> list = new ArrayList<>();
        String sql = "SELECT * FROM course_contents WHERE course_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CourseContent c = new CourseContent();
                c.setId(rs.getLong("id"));
                c.setCourseId(rs.getLong("course_id"));
                c.setFileName(rs.getString("file_name"));
                c.setFilePath(rs.getString("file_path"));
                list.add(c);
            }
            return list;

        } catch (Exception e) {
            throw new RuntimeException("Error loading course contents", e);
        }
    }
}
