package com.lms.model;

public class Student extends User {
    public Student() { super(); }
    public Student(Long id, String name, String email, String password) {
        super(id, name, email, password, Role.STUDENT);
    }
}
