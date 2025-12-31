USE lms_db;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(150),
    email VARCHAR(150) UNIQUE,
    password VARCHAR(150),
    role VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255),
    description TEXT,
    instructor_id BIGINT
    );

CREATE TABLE IF NOT EXISTS enrollments (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           student_id BIGINT,
                                           course_id BIGINT,
                                           completed BOOLEAN DEFAULT FALSE,
                                           UNIQUE KEY student_course (student_id, course_id)
    );
