package com.lms.app.gui;

import com.lms.model.User;
import com.lms.service.CourseService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Simple instructor dashboard: list & create own courses.
 */
public class InstructorDashboardFrame extends JFrame {
    private final User instructor;
    private final CourseService courseService = new CourseService();

    public InstructorDashboardFrame(User instructor) {
        this.instructor = instructor;
        setTitle("Instructor Dashboard - " + instructor.getName());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCreate = new JButton("Create Course");
        JButton btnList = new JButton("List My Courses");
        top.add(btnCreate);
        top.add(btnList);

        add(top, BorderLayout.NORTH);

        btnCreate.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Course title:");
            if (title == null || title.trim().isEmpty()) return;
            String desc = JOptionPane.showInputDialog(this, "Course description:");
            if (desc == null) desc = "";
            courseService.createCourse(title, desc, instructor.getId());
            JOptionPane.showMessageDialog(this, "Course created");
        });

        btnList.addActionListener(e -> {
            List<com.lms.model.Course> list = courseService.findByInstructor(instructor.getId());
            StringBuilder sb = new StringBuilder();
            for (com.lms.model.Course c : list) {
                sb.append(c.getId()).append(": ").append(c.getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No courses" : sb.toString());
        });
    }
}
