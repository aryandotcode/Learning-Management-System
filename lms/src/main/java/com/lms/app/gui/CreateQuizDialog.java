package com.lms.app.gui;

import com.lms.model.Quiz;
import com.lms.service.QuizService;

import javax.swing.*;
import java.awt.*;

public class CreateQuizDialog extends JDialog {

    private final Long courseId;
    private final QuizService quizService = new QuizService();

    public CreateQuizDialog(Frame owner, Long courseId) {
        super(owner, "Create Quiz", true);
        this.courseId = courseId;
        setSize(400, 200);
        setLocationRelativeTo(owner);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(null);

        JLabel lbl = new JLabel("Quiz Title:");
        lbl.setBounds(30, 30, 100, 25);
        panel.add(lbl);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(130, 30, 200, 25);
        panel.add(txtTitle);

        JButton btnCreate = new JButton("Create Quiz");
        btnCreate.setBounds(130, 80, 130, 30);
        panel.add(btnCreate);

        btnCreate.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Quiz title required");
                return;
            }

            Quiz quiz = new Quiz();
            quiz.setCourseId(courseId);
            quiz.setTitle(title);

            Quiz savedQuiz = quizService.createQuiz(quiz);

            // 👉 quiz ban gaya → ab question add window open
            AddQuestionDialog dialog =
                    new AddQuestionDialog((Frame) getOwner(), savedQuiz.getId());

            dialog.setVisible(true);
            dispose();
        });

        add(panel);
    }
}
