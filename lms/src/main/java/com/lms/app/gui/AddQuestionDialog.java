package com.lms.app.gui;

import com.lms.model.Question;
import com.lms.service.QuestionService;

import javax.swing.*;
import java.awt.*;

public class AddQuestionDialog extends JDialog {

    private final Long quizId;
    private final QuestionService questionService = new QuestionService();

    public AddQuestionDialog(Frame owner, Long quizId) {
        super(owner, "Add Question", true);
        this.quizId = quizId;
        setSize(450, 380);
        setLocationRelativeTo(owner);
        initUI();
    }

    private void initUI() {

        JPanel panel = new JPanel(null);

        JLabel qLbl = new JLabel("Question:");
        qLbl.setBounds(20, 20, 100, 25);
        panel.add(qLbl);

        JTextField txtQ = new JTextField();
        txtQ.setBounds(120, 20, 280, 25);
        panel.add(txtQ);

        JTextField a = field(panel, "Option A:", 60);
        JTextField b = field(panel, "Option B:", 100);
        JTextField c = field(panel, "Option C:", 140);
        JTextField d = field(panel, "Option D:", 180);

        JLabel correctLbl = new JLabel("Correct (A/B/C/D):");
        correctLbl.setBounds(20, 220, 150, 25);
        panel.add(correctLbl);

        JTextField correct = new JTextField();
        correct.setBounds(180, 220, 50, 25);
        panel.add(correct);

        JButton btnSave = new JButton("Save Question");
        btnSave.setBounds(80, 270, 130, 30);
        panel.add(btnSave);

        JButton btnDone = new JButton("Done");
        btnDone.setBounds(230, 270, 100, 30);
        panel.add(btnDone);

        btnSave.addActionListener(e -> {
            Question q = new Question();
            q.setQuizId(quizId);
            q.setQuestion(txtQ.getText());
            q.setOptionA(a.getText());
            q.setOptionB(b.getText());
            q.setOptionC(c.getText());
            q.setOptionD(d.getText());
            q.setCorrectOption(correct.getText().toUpperCase().charAt(0));

            questionService.addQuestion(q);

            JOptionPane.showMessageDialog(this, "Question added");

            txtQ.setText("");
            a.setText(""); b.setText(""); c.setText(""); d.setText(""); correct.setText("");
        });

        btnDone.addActionListener(e -> dispose());

        add(panel);
    }

    private JTextField field(JPanel p, String label, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 100, 25);
        p.add(l);

        JTextField t = new JTextField();
        t.setBounds(120, y, 280, 25);
        p.add(t);
        return t;
    }
}
