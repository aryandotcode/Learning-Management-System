package com.lms.app.gui;

import com.lms.model.User;
import com.lms.model.Course;
import com.lms.service.CourseService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InstructorDashboardFrame extends JFrame {

    private final User instructor;
    private final CourseService courseService = new CourseService();

    public InstructorDashboardFrame(User instructor) {
        this.instructor = instructor;
        setTitle("Instructor Dashboard - " + instructor.getName());
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnCreateCourse = new JButton("Create Course");
        JButton btnListCourses = new JButton("List My Courses");
        JButton btnAddContent = new JButton("Add Course Content");
        JButton btnCreateQuiz = new JButton("Create Quiz");
        JButton btnViewQuiz = new JButton("View Quizzes");
        JButton btnViewResults = new JButton("View Quiz Results"); // ✅ NEW

        top.add(btnCreateCourse);
        top.add(btnListCourses);
        top.add(btnAddContent);
        top.add(btnCreateQuiz);
        top.add(btnViewQuiz);
        top.add(btnViewResults); // ✅

        add(top, BorderLayout.NORTH);

        // Create Course
        btnCreateCourse.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Course title:");
            if (title == null || title.trim().isEmpty()) return;

            String desc = JOptionPane.showInputDialog(this, "Course description:");
            if (desc == null) desc = "";

            courseService.createCourse(title, desc, instructor.getId());
            JOptionPane.showMessageDialog(this, "Course created successfully");
        });

        // List Courses
        btnListCourses.addActionListener(e -> {
            List<Course> list = courseService.findByInstructor(instructor.getId());
            StringBuilder sb = new StringBuilder();
            for (Course c : list) {
                sb.append(c.getId()).append(" : ").append(c.getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(this,
                    sb.length() == 0 ? "No courses" : sb.toString());
        });

        // Add Content
        btnAddContent.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Course ID:");
            if (input == null) return;
            new AddCourseContentDialog(this, Long.parseLong(input)).setVisible(true);
        });

        // Create Quiz
        btnCreateQuiz.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Course ID:");
            if (input == null) return;
            new CreateQuizDialog(this, Long.parseLong(input)).setVisible(true);
        });

        // View Quizzes
        btnViewQuiz.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Course ID:");
            if (input == null) return;
            new ViewQuizDialog(this, Long.parseLong(input)).setVisible(true);
        });

        // ✅ View Quiz Results (NEW & IMPORTANT)
        btnViewResults.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Quiz ID:");
            if (input == null || input.trim().isEmpty()) return;

            try {
                Long quizId = Long.parseLong(input.trim());
                new ViewQuizResultDialog(this, quizId).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Quiz ID");
            }
        });
    }
}
