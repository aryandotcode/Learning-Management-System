package com.lms.app.gui;

import com.lms.service.CourseContentService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AddCourseContentDialog extends JDialog {

    private final Long courseId;
    private final CourseContentService contentService = new CourseContentService();

    public AddCourseContentDialog(Frame owner, Long courseId) {
        super(owner, "Add Course Content", true);
        this.courseId = courseId;
        setSize(420, 220);
        setLocationRelativeTo(owner);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(null);

        JLabel lblFile = new JLabel("Select File:");
        lblFile.setBounds(20, 30, 100, 25);
        panel.add(lblFile);

        JTextField txtFilePath = new JTextField();
        txtFilePath.setBounds(20, 60, 260, 25);
        txtFilePath.setEditable(false);
        panel.add(txtFilePath);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(290, 60, 90, 25);
        panel.add(btnBrowse);

        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(120, 120, 100, 30);
        panel.add(btnUpload);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(230, 120, 100, 30);
        panel.add(btnCancel);

        final File[] selectedFile = {null};

        btnBrowse.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = chooser.getSelectedFile();
                txtFilePath.setText(selectedFile[0].getAbsolutePath());
            }
        });

        btnUpload.addActionListener(e -> {
            if (selectedFile[0] == null) {
                JOptionPane.showMessageDialog(this, "Please select a file");
                return;
            }

            // ✅ FINAL correct constructor usage
            contentService.uploadContent(
                    courseId,
                    selectedFile[0].getName(),
                    selectedFile[0].getAbsolutePath()
            );

            JOptionPane.showMessageDialog(this, "Content uploaded successfully");
            dispose();
        });

        btnCancel.addActionListener(e -> dispose());

        add(panel);
    }
}
