package com.lms.app.gui;

import com.lms.model.Question;
import com.lms.service.QuestionService;
import com.lms.service.QuizResultService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AttemptQuizDialog extends JDialog {

    public AttemptQuizDialog(Frame owner, Long quizId, Long studentId) {
        super(owner, "Attempt Quiz", true);
        setSize(550, 450);
        setLocationRelativeTo(owner);

        QuestionService questionService = new QuestionService();
        QuizResultService resultService = new QuizResultService();

        List<Question> questions = questionService.getQuestionsByQuiz(quizId);

        JPanel panel = new JPanel(new GridLayout(questions.size() + 1, 1));
        int[] score = {0};

        for (Question q : questions) {
            JPanel qPanel = new JPanel(new GridLayout(5, 1));
            qPanel.setBorder(BorderFactory.createTitledBorder(q.getQuestion()));

            ButtonGroup bg = new ButtonGroup();
            JRadioButton a = new JRadioButton("A. " + q.getOptionA());
            JRadioButton b = new JRadioButton("B. " + q.getOptionB());
            JRadioButton c = new JRadioButton("C. " + q.getOptionC());
            JRadioButton d = new JRadioButton("D. " + q.getOptionD());

            bg.add(a); bg.add(b); bg.add(c); bg.add(d);
            qPanel.add(a); qPanel.add(b); qPanel.add(c); qPanel.add(d);

            JButton submit = new JButton("Submit");
            submit.addActionListener(e -> {
                if ((q.getCorrectOption()=='A' && a.isSelected()) ||
                        (q.getCorrectOption()=='B' && b.isSelected()) ||
                        (q.getCorrectOption()=='C' && c.isSelected()) ||
                        (q.getCorrectOption()=='D' && d.isSelected())) {
                    score[0]++;
                }
                submit.setEnabled(false);
            });

            qPanel.add(submit);
            panel.add(qPanel);
        }

        JButton finish = new JButton("Finish Quiz");
        finish.addActionListener(e -> {
            resultService.saveResult(quizId, studentId, score[0]);
            JOptionPane.showMessageDialog(this,
                    "Quiz Completed!\nScore: " + score[0] + "/" + questions.size());
            dispose();
        });

        add(new JScrollPane(panel), BorderLayout.CENTER);
        add(finish, BorderLayout.SOUTH);
    }
}
