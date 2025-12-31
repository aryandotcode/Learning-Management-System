package com.lms.service;

import com.lms.dao.CourseDao;
import com.lms.dao.EnrollmentDao;
import com.lms.dao.jdbc.JdbcCourseDao;
import com.lms.dao.jdbc.JdbcEnrollmentDao;
import com.lms.model.Course;
import com.lms.model.Enrollment;

import java.util.*;

/**
 * Course service extended with enrollment persistence.
 */
public class CourseService {
    private final CourseDao courseDao = new JdbcCourseDao();
    private final EnrollmentDao enrollmentDao = new JdbcEnrollmentDao();
    private final Map<Long, Course> cache = Collections.synchronizedMap(new HashMap<>());

    public Course createCourse(String title, String description, Long instructorId) {
        Course c = new Course(null, title, description, instructorId);
        Course saved = courseDao.save(c);
        cache.put(saved.getId(), saved);
        return saved;
    }

    public List<Course> listAll() {
        List<Course> fromDb = courseDao.findAll();
        for (Course c : fromDb) cache.put(c.getId(), c);
        return fromDb;
    }

    public List<Course> findByInstructor(Long instructorId) {
        return courseDao.findByInstructorId(instructorId);
    }

    public Optional<Course> findById(Long id) {
        if (cache.containsKey(id)) return Optional.of(cache.get(id));
        return courseDao.findById(id);
    }

    public void flushCacheToDb() {
        synchronized (cache) {
            for (Course c : cache.values()) {
                courseDao.update(c);
            }
        }
    }

    // New: persistent enroll method. Throws runtime exception if already enrolled.
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // check existing
        if (enrollmentDao.findByStudentAndCourse(studentId, courseId).isPresent()) {
            throw new RuntimeException("Student already enrolled in course " + courseId);
        }
        Enrollment e = new Enrollment(studentId, courseId);
        return enrollmentDao.save(e);
    }

    // Get enrollments for a student
    public List<Enrollment> getEnrollmentsForStudent(Long studentId) {
        return enrollmentDao.findByStudentId(studentId);
    }
}
