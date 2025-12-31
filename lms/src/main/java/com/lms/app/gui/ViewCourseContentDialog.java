package com.lms.app.gui;

import com.lms.model.CourseContent;
import com.lms.service.CourseContentService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ViewCourseContentDialog extends JDialog {

    private final Long courseId;
    private final CourseContentService contentService = new CourseContentService();

    public ViewCourseContentDialog(Frame owner, Long courseId) {
        super(owner, "Course Contents", true);
        this.courseId = courseId;
        setSize(520, 350);
        setLocationRelativeTo(owner);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<CourseContent> model = new DefaultListModel<>();
        List<CourseContent> contents = contentService.getContentsByCourse(courseId);
        for (CourseContent c : contents) {
            model.addElement(c);
        }

        JList<CourseContent> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ✅ Custom renderer using fileName (NOT title)
        list.setCellRenderer((lst, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value.getFileName());
            if (isSelected) {
                lbl.setBackground(new Color(220, 235, 255));
                lbl.setOpaque(true);
            }
            return lbl;
        });

        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        JButton btnOpen = new JButton("Open / Download");
        JButton btnClose = new JButton("Close");

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnOpen);
        bottom.add(btnClose);

        panel.add(bottom, BorderLayout.SOUTH);

        btnOpen.addActionListener(e -> {
            CourseContent selected = list.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a file first");
                return;
            }

            try {
                // ✅ Open file directly from system
                File file = new File(selected.getFilePath());
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(this, "File not found on system");
                    return;
                }
                Desktop.getDesktop().open(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnClose.addActionListener(e -> dispose());

        add(panel);
    }
}
