package com.lms.app;

import com.lms.app.gui.LoginFrame;
import com.lms.util.DBConnection;

import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Entry point for the LMS desktop application (Review 1).
 */
public class MainApp {
    public static void main(String[] args) {
        // Quick DB connectivity check (optional)
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("DB connection OK");
            }
        } catch (SQLException ex) {
            System.err.println("Database connection failed: " + ex.getMessage());
            // continue — UI will still start but DB operations will fail
        }

        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
    }
}
