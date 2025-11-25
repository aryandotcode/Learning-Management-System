package com.lms.app;

import com.lms.service.CourseService;
import com.lms.thread.EnrollmentProcessor;

public class ConcurrentTest {
    public static void main(String[] args) {
        CourseService cs = new CourseService();
        EnrollmentProcessor ep = new EnrollmentProcessor(cs);

        Runnable task = () -> {
            cs.createCourse("Concurrent Course", "desc", null);
            System.out.println("Created by " + Thread.currentThread().getName());
        };

        ep.simulateConcurrentEnrollments(task, 5); // 5 threads
    }
}
