package com.lms.app.gui;

import com.lms.model.Quiz;
import com.lms.service.QuizService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewQuizDialog extends JDialog {

    public ViewQuizDialog(Frame owner, Long courseId) {
        super(owner, "Quizzes", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);

        QuizService quizService = new QuizService();
        List<Quiz> quizzes = quizService.getQuizzesByCourse(courseId);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Quiz q : quizzes) {
            model.addElement(q.getId() + " : " + q.getTitle());
        }

        add(new JScrollPane(new JList<>(model)));
    }
}
