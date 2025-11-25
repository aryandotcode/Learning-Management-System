package com.lms.dao;

import com.lms.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDao extends BaseDao<Enrollment, Long> {
    Optional<Enrollment> findByStudentAndCourse(Long studentId, Long courseId);
    List<Enrollment> findByStudentId(Long studentId);
}
