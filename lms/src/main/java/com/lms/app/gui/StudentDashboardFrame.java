package com.lms.app.gui;

import com.lms.model.User;
import com.lms.model.Course;
import com.lms.service.CourseService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentDashboardFrame extends JFrame {

    private final User student;
    private final CourseService courseService = new CourseService();

    public StudentDashboardFrame(User student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getName());
        setSize(850, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnEnroll = new JButton("Enroll Course");
        JButton btnViewContent = new JButton("View Content");
        JButton btnAttemptQuiz = new JButton("Attempt Quiz");
        JButton btnViewResult = new JButton("View My Quiz Result"); // ✅ NEW

        top.add(btnEnroll);
        top.add(btnViewContent);
        top.add(btnAttemptQuiz);
        top.add(btnViewResult);

        add(top, BorderLayout.NORTH);

        // Enroll Course
        btnEnroll.addActionListener(e -> {
            List<Course> list = courseService.listAll();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No courses available");
                return;
            }

            String[] arr = list.stream()
                    .map(c -> c.getId() + ":" + c.getTitle())
                    .toArray(String[]::new);

            String choice = (String) JOptionPane.showInputDialog(
                    this, "Select course", "Enroll",
                    JOptionPane.PLAIN_MESSAGE, null, arr, arr[0]);

            if (choice == null) return;
            Long id = Long.parseLong(choice.split(":")[0]);
            courseService.enrollStudent(student.getId(), id);
            JOptionPane.showMessageDialog(this, "Enrolled Successfully");
        });

        // View Content
        btnViewContent.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Course ID:");
            if (input == null) return;
            new ViewCourseContentDialog(this, Long.parseLong(input)).setVisible(true);
        });

        // Attempt Quiz
        btnAttemptQuiz.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Quiz ID:");
            if (input == null) return;
            new AttemptQuizDialog(
                    this,
                    Long.parseLong(input),
                    student.getId()
            ).setVisible(true);
        });

        // ✅ View Own Result
        btnViewResult.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Quiz ID:");
            if (input == null) return;
            new ViewMyQuizResultDialog(
                    this,
                    Long.parseLong(input),
                    student.getId()
            ).setVisible(true);
        });
    }
}
