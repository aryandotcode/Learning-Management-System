package com.lms.service;

import com.lms.dao.UserDao;
import com.lms.dao.jdbc.JdbcUserDao;
import com.lms.model.*;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserDao userDao = new JdbcUserDao();

    public User createUser(String name, String email, String password, Role role) {
        User u;
        if (role == Role.ADMIN) u = new Admin(null, name, email, password);
        else if (role == Role.INSTRUCTOR) u = new Instructor(null, name, email, password);
        else u = new Student(null, name, email, password);

        return userDao.save(u);
    }

    public Optional<User> authenticate(String email, String password) {
        Optional<User> opt = userDao.findByEmail(email);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            return opt;
        }
        return Optional.empty();
    }

    public List<User> listAll() {
        return userDao.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
