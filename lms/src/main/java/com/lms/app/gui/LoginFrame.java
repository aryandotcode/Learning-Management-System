package com.lms.app.gui;

import com.lms.model.Role;
import com.lms.model.User;
import com.lms.service.UserService;

import javax.swing.*;
import java.awt.*;

/**
 * Very simple login frame. On successful login opens role-based dashboards.
 */
public class LoginFrame extends JFrame {
    private final UserService userService = new UserService();

    public LoginFrame() {
        setTitle("LMS - Login");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 80, 25);
        panel.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(110, 30, 250, 25);
        panel.add(txtEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 70, 80, 25);
        panel.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(110, 70, 250, 25);
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(110, 110, 100, 30);
        panel.add(btnLogin);

        JButton btnRegisterAdmin = new JButton("Create Admin (seed)");
        btnRegisterAdmin.setBounds(220, 110, 170, 30);
        panel.add(btnRegisterAdmin);

        JLabel lblMsg = new JLabel("");
        lblMsg.setBounds(30, 150, 360, 25);
        panel.add(lblMsg);

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();
            if (email.isEmpty() || pass.isEmpty()) {
                lblMsg.setText("Please enter email and password");
                return;
            }
            userService.findByEmail(email).ifPresentOrElse(user -> {
                if (user.getPassword().equals(pass)) {
                    // success
                    openDashboardFor(user);
                    dispose();
                } else {
                    lblMsg.setText("Invalid credentials");
                }
            }, () -> lblMsg.setText("User not found"));
        });

        // create a default admin user if not present
        btnRegisterAdmin.addActionListener(e -> {
            String seedEmail = "admin@example.com";
            userService.findByEmail(seedEmail).ifPresentOrElse(u -> {
                JOptionPane.showMessageDialog(this, "Admin already exists: " + seedEmail);
            }, () -> {
                userService.createUser("Admin User", seedEmail, "admin123", Role.ADMIN);
                JOptionPane.showMessageDialog(this, "Seed admin created: " + seedEmail + " / admin123");
            });
        });

        add(panel);
    }

    private void openDashboardFor(User user) {
        if (user.getRole() == Role.ADMIN) {
            SwingUtilities.invokeLater(() -> new AdminDashboardFrame(user).setVisible(true));
        } else if (user.getRole() == Role.INSTRUCTOR) {
            SwingUtilities.invokeLater(() -> new InstructorDashboardFrame(user).setVisible(true));
        } else {
            SwingUtilities.invokeLater(() -> new StudentDashboardFrame(user).setVisible(true));
        }
    }
}
