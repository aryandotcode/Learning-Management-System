package com.lms.app;

import com.lms.service.CourseService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentEnrollTest {
    public static void main(String[] args) {
        CourseService cs = new CourseService();
        final long studentId = 3L; // change to an actual student id from your DB
        final long courseId = 1L; // change to an actual course id
        ExecutorService ex = Executors.newFixedThreadPool(5);
        Runnable task = () -> {
            try {
                cs.enrollStudent(studentId, courseId);
                System.out.println("Enrolled by " + Thread.currentThread().getName());
            } catch (Exception e) {
                System.out.println("Enroll failed by " + Thread.currentThread().getName() + ": " + e.getMessage());
            }
        };
        for (int i = 0; i < 5; i++) ex.submit(task);
        ex.shutdown();
    }
}
