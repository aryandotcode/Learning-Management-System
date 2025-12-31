package com.lms.app.gui;

import com.lms.model.QuizResult;
import com.lms.service.QuizResultService;

import javax.swing.*;
import java.awt.*;

public class ViewMyQuizResultDialog extends JDialog {

    public ViewMyQuizResultDialog(Frame owner, Long quizId, Long studentId) {
        super(owner, "My Quiz Result", true);
        setSize(300, 200);
        setLocationRelativeTo(owner);

        QuizResultService service = new QuizResultService();
        QuizResult result = service.getResultByQuizAndStudent(quizId, studentId);

        JLabel lbl;

        if (result == null) {
            lbl = new JLabel("You have not attempted this quiz yet.");
        } else {
            lbl = new JLabel("Your Score: " + result.getScore());
        }

        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl);
    }
}
