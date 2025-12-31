package com.lms.thread;

import com.lms.service.CourseService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple demo harness that simulates concurrent enrollments.
 * For this simple project we only show how to run multiple threads.
 * Actual enroll implementation (DB writes) can be added to CourseService/DAO.
 */
public class EnrollmentProcessor {
    private final CourseService courseService;

    public EnrollmentProcessor(CourseService cs) {
        this.courseService = cs;
    }

    public void simulateConcurrentEnrollments(Runnable enrollmentTask, int threads) {
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            exec.submit(enrollmentTask);
        }
        exec.shutdown();
    }
}
