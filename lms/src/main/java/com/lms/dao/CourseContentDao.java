package com.lms.dao;

import com.lms.model.CourseContent;
import java.util.List;

public interface CourseContentDao {
    void save(CourseContent content);
    List<CourseContent> findByCourseId(Long courseId);
}
