package com.lms.dao;

import com.lms.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User, Long> {
    Optional<User> findByEmail(String email);
}
