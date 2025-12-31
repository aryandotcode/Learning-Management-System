package com.lms.app.gui;

import com.lms.model.QuizResult;
import com.lms.model.User;
import com.lms.service.QuizResultService;
import com.lms.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewQuizResultDialog extends JDialog {

    public ViewQuizResultDialog(Frame owner, Long quizId) {
        super(owner, "Quiz Results", true);
        setSize(450, 300);
        setLocationRelativeTo(owner);

        QuizResultService resultService = new QuizResultService();
        UserService userService = new UserService();

        List<QuizResult> results = resultService.getResultsByQuiz(quizId);

        DefaultListModel<String> model = new DefaultListModel<>();

        if (results.isEmpty()) {
            model.addElement("No student has attempted this quiz yet.");
        } else {
            for (QuizResult r : results) {
                User student = userService.findById(r.getStudentId()).orElse(null);

                String name = (student != null)
                        ? student.getName()
                        : "Unknown Student";

                model.addElement(
                        "Student: " + name +
                                " | Score: " + r.getScore()
                );
            }
        }

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(list), BorderLayout.CENTER);
    }
}
