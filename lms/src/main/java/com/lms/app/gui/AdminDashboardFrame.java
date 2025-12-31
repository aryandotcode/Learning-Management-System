package com.lms.app.gui;

import com.lms.model.User;
import com.lms.model.Role;
import com.lms.service.UserService;
import com.lms.service.CourseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Simple Admin Dashboard to list users and create courses.
 * Includes a Transaction Demo button to show JDBC commit/rollback behavior.
 */
public class AdminDashboardFrame extends JFrame {
    private final User admin;
    private final UserService userService = new UserService();
    private final CourseService courseService = new CourseService();

    private final DefaultTableModel userTableModel = new DefaultTableModel(
            new Object[]{"ID", "Name", "Email", "Role"}, 0);

    public AdminDashboardFrame(User admin) {
        this.admin = admin;
        setTitle("Admin Dashboard - " + admin.getName());
        setSize(900, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadUsers();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAddUser = new JButton("Add User");
        JButton btnDeleteUser = new JButton("Delete Selected User");
        JButton btnListCourses = new JButton("List Courses");
        JButton btnTxnDemo = new JButton("Transaction Demo"); // NEW
        top.add(btnAddUser);
        top.add(btnDeleteUser);
        top.add(btnListCourses);
        top.add(btnTxnDemo);

        JTable table = new JTable(userTableModel);
        JScrollPane scroll = new JScrollPane(table);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Add User action
        btnAddUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Name:");
            if (name == null || name.trim().isEmpty()) return;
            String email = JOptionPane.showInputDialog(this, "Email:");
            if (email == null || email.trim().isEmpty()) return;
            String pass = JOptionPane.showInputDialog(this, "Password:");
            if (pass == null || pass.trim().isEmpty()) return;
            String[] opts = {"ADMIN", "INSTRUCTOR", "STUDENT"};
            String roleStr = (String) JOptionPane.showInputDialog(this, "Role:", "Select Role",
                    JOptionPane.PLAIN_MESSAGE, null, opts, opts[2]);
            if (roleStr == null) return;
            Role r = Role.valueOf(roleStr);
            userService.createUser(name, email, pass, r);
            loadUsers();
            JOptionPane.showMessageDialog(this, "User created: " + email);
        });

        // Delete user action
        btnDeleteUser.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel == -1) {
                JOptionPane.showMessageDialog(this, "Select a user row first");
                return;
            }
            Object idObj = userTableModel.getValueAt(sel, 0);
            Long id;
            if (idObj instanceof Long) id = (Long) idObj;
            else id = Long.parseLong(idObj.toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Delete user id: " + id + " ?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            userService.deleteUser(id);
            loadUsers();
            JOptionPane.showMessageDialog(this, "User deleted: id=" + id);
        });

        // List courses action
        btnListCourses.addActionListener(e -> {
            List<com.lms.model.Course> courses = courseService.listAll();
            StringBuilder sb = new StringBuilder();
            for (com.lms.model.Course c : courses) {
                sb.append(c.getId()).append(": ").append(c.getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No courses" : sb.toString());
        });

        // Transaction Demo action (force rollback)
        btnTxnDemo.addActionListener(ev -> {
            try {
                // forceRollback = true to demonstrate rollback behavior
                com.lms.util.TransactionDemo.createUserAndCourseWithRollbackDemo(
                        "TxnInstructor", "txn_instructor@example.com", "txnpass", "Txn Course", true);
                // If commit happened (unexpected), inform user
                JOptionPane.showMessageDialog(this, "Transaction committed (unexpected).");
            } catch (Exception ex) {
                // Expected path: rollback happened
                JOptionPane.showMessageDialog(this, "Transaction rolled back (expected): " + ex.getMessage());
            }
            // refresh table so examiner can verify DB (no new user added)
            loadUsers();
        });
    }

    private void loadUsers() {
        userTableModel.setRowCount(0);
        List<User> users = userService.listAll();
        for (User u : users) {
            userTableModel.addRow(new Object[]{u.getId(), u.getName(), u.getEmail(), u.getRole()});
        }
    }
}
