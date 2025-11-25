package com.lms.dao.jdbc;

import com.lms.dao.UserDao;
import com.lms.model.*;
import com.lms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple JDBC implementation for UserDao.
 * Synchronized on write methods to avoid race conditions.
 */
public class JdbcUserDao implements UserDao {

    @Override
    public synchronized User save(User user) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException("Error saving user: " + ex.getMessage(), ex);
        }
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET name=?, email=?, password=?, role=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException("Error updating user: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error deleting user: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT id,name,email,password,role FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Error finding user by id: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id,name,email,password,role FROM users";
        List<User> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Error listing users: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id,name,email,password,role FROM users WHERE email=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Error finding user by email: " + ex.getMessage(), ex);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String roleStr = rs.getString("role");
        Role role = Role.valueOf(roleStr);
        switch (role) {
            case ADMIN:
                return new Admin(id, name, email, password);
            case INSTRUCTOR:
                return new Instructor(id, name, email, password);
            default:
                return new Student(id, name, email, password);
        }
    }
}
