package com.lms.model;

public class Admin extends User {
    public Admin() { super(); }
    public Admin(Long id, String name, String email, String password) {
        super(id, name, email, password, Role.ADMIN);
    }
}
