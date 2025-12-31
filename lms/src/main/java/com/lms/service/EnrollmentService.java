package com.lms.service;

import com.lms.dao.EnrollmentDao;
import com.lms.dao.jdbc.JdbcEnrollmentDao;
import com.lms.model.Enrollment;

import java.util.List;

/**
 * Service layer for Enrollment related operations.
 * Acts as a bridge between GUI / Web and DAO layer.
 */
public class EnrollmentService {

    private final EnrollmentDao enrollmentDao = new JdbcEnrollmentDao();

    /**
     * Enroll a student into a course
     */
    public void enrollStudent(Long studentId, Long courseId) {
        // prevent duplicate enrollment
        if (enrollmentDao.findByStudentAndCourse(studentId, courseId).isPresent()) {
            throw new RuntimeException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(studentId, courseId);
        enrollmentDao.save(enrollment);
    }

    /**
     * Get all enrollments of a particular student
     */
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentDao.findByStudentId(studentId);
    }

    /**
     * Check whether a student is already enrolled in a course
     */
    public boolean isAlreadyEnrolled(Long studentId, Long courseId) {
        return enrollmentDao.findByStudentAndCourse(studentId, courseId).isPresent();
    }

    // 🔥 THIS METHOD WAS MISSING (needed for Admin Analytics)
    public List<Enrollment> findAll() {
        return enrollmentDao.findAll();
    }
}
