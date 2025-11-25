package com.lms.dao;

import com.lms.model.Course;

import java.util.List;

public interface CourseDao extends BaseDao<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
}
