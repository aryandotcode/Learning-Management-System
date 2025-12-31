package com.lms.model;

public class Instructor extends User {
    public Instructor() { super(); }
    public Instructor(Long id, String name, String email, String password) {
        super(id, name, email, password, Role.INSTRUCTOR);
    }
}
