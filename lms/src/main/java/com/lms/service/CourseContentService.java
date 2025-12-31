package com.lms.service;

import com.lms.dao.CourseContentDao;
import com.lms.dao.jdbc.JdbcCourseContentDao;
import com.lms.model.CourseContent;

import java.util.List;

/**
 * Service layer for Course Content management.
 */
public class CourseContentService {

    private final CourseContentDao contentDao = new JdbcCourseContentDao();

    /**
     * Upload new file content (PDF / Notes)
     */
    public void uploadContent(Long courseId, String fileName, String filePath) {
        CourseContent content = new CourseContent(courseId, fileName, filePath);
        contentDao.save(content);
    }

    /**
     * Get all contents of a course
     */
    public List<CourseContent> getContentsByCourse(Long courseId) {
        return contentDao.findByCourseId(courseId);
    }
}
