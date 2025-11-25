package com.lms.app.gui;

import com.lms.model.User;
import com.lms.model.Course;
import com.lms.service.CourseService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Student dashboard with persistent enroll.
 */
public class StudentDashboardFrame extends JFrame {
    private final User student;
    private final CourseService courseService = new CourseService();

    public StudentDashboardFrame(User student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getName());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnList = new JButton("List All Courses");
        top.add(btnList);
        add(top, BorderLayout.NORTH);

        btnList.addActionListener(e -> {
            List<Course> list = courseService.listAll();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No courses available");
                return;
            }
            String[] arr = list.stream().map(c -> c.getId() + ": " + c.getTitle()).toArray(String[]::new);
            String choice = (String) JOptionPane.showInputDialog(this, "Select course to enroll", "Enroll",
                    JOptionPane.PLAIN_MESSAGE, null, arr, arr[0]);
            if (choice == null) return;
            Long id = Long.parseLong(choice.split(":")[0]);
            try {
                courseService.enrollStudent(student.getId(), id);
                JOptionPane.showMessageDialog(this, "Enrollment saved for course id: " + id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enrollment failed: " + ex.getMessage());
            }
        });
    }
}
