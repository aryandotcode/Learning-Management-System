package com.lms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Demonstrates a JDBC transaction: insert user and course in a single transaction,
 * then optionally force an exception to rollback.
 */
public class TransactionDemo {

    public static void createUserAndCourseWithRollbackDemo(String userName, String userEmail, String pass,
                                                           String courseTitle, boolean forceRollback) {
        String insertUser = "INSERT INTO users (name, email, password, role) VALUES (?,?,?,?)";
        String insertCourse = "INSERT INTO courses (title, description, instructor_id) VALUES (?,?,?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pu = conn.prepareStatement(insertUser, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                pu.setString(1, userName);
                pu.setString(2, userEmail);
                pu.setString(3, pass);
                pu.setString(4, "INSTRUCTOR");
                pu.executeUpdate();
                long userId = -1;
                try (java.sql.ResultSet rs = pu.getGeneratedKeys()) {
                    if (rs.next()) userId = rs.getLong(1);
                }

                // insert course with instructor_id = userId
                try (PreparedStatement pc = conn.prepareStatement(insertCourse)) {
                    pc.setString(1, courseTitle);
                    pc.setString(2, "Created in transaction demo");
                    if (userId > 0) pc.setLong(3, userId);
                    else pc.setNull(3, java.sql.Types.BIGINT);
                    pc.executeUpdate();
                }

                if (forceRollback) {
                    throw new RuntimeException("Forcing rollback for demo");
                }

                conn.commit();
            }
        } catch (Exception ex) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ignore) {}
            throw new RuntimeException("Transaction failed and rolled back: " + ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignore) {}
            }
        }
    }
}
