#  Learning Management System (LMS)

**Java GUI + Web + JDBC + OOP + Multithreading Project**  
**Author:** Aryan Gupta (B.Tech 2nd Year)  
**Tech Stack:** Java (JDK 25), Swing GUI, Servlets & JSP, JDBC, MySQL  
**Environment:** IntelliJ IDEA 2025, MySQL Workbench 8  

---

##  Overview
This project is a **complete Learning Management System (LMS)** developed using **Java** that supports both:

-  **Desktop GUI (Swing)**
-  **Web Application (Servlets & JSP)**

The system allows **Admins**, **Instructors**, and **Students** to interact with courses, quizzes, enrollments, and results through role-based dashboards.

The project is built using **clean OOP principles**, **DAO architecture**, **JDBC with PreparedStatements**, **transaction management**, and **multithreading with synchronization** to ensure reliability and scalability.

---

##  Core Features

### 👨‍💼 Admin
- Create Admin, Instructor, and Student accounts
- Delete users
- View all users and courses
- Perform transaction demo (commit & rollback)
- Monitor system analytics

### 👨‍🏫 Instructor
- Login with secure credentials
- Create and manage courses
- Upload course content (PDF / Video / Notes)
- Create quizzes for courses
- Add MCQ-based questions
- View quiz attempts and student scores

### 🎓 Student
- Login into student dashboard
- View available courses
- Enroll into courses (persistent enrollment)
- Access course content
- Attempt quizzes
- View quiz score after submission

---

##  Quiz & Result System
- MCQ-based quizzes linked to courses
- Automatic score calculation
- Quiz results stored persistently
- Instructors can view **all student results**
- Students can view **their own results**

---

##  Technical Architecture

###  OOP Design
| Concept | Usage |
|------|------|
| Inheritance | `User → Admin / Instructor / Student` |
| Polymorphism | Role-based dashboard loading |
| Encapsulation | Private fields with getters/setters |
| Interfaces | DAO layer (`BaseDao<T,ID>`) |
| Abstraction | Service layer between UI & DAO |

---

###  Collections & Generics
- `List<User>`, `List<Course>`, `List<Enrollment>`
- Generic DAO interfaces
- In-memory course cache using `Map<Long, Course>`

---

###  Multithreading & Synchronization
- Background **AutoSaveThread** for cache flush
- Concurrent enrollment processing
- Synchronized DAO methods to prevent race conditions
- Verified using concurrent enrollment test

---

###  JDBC & Database Operations
- JDBC with **PreparedStatement**
- Complete CRUD operations
- Safe against SQL injection
- Centralized DB connection utility

---

###  Transaction Management
- Multi-step DB operations handled in single transaction
- Explicit `commit()` and `rollback()`
- Demonstrated via admin transaction demo
- Verified directly in MySQL Workbench

---

##  Web Application (Servlets & JSP)
- Login & authentication using `HttpSession`
- Role-based routing via DashboardServlet
- Separate servlets for Admin, Instructor & Student
- Shared Service & DAO layer with GUI version
- Clean MVC-style separation

---

##  Error Handling & Validation
- Invalid credentials handled gracefully
- Duplicate enrollments prevented
- Empty and invalid inputs validated
- Database failures do not crash application
- User-friendly messages displayed

---

##  Database Schema

### Tables
- `users (id, name, email, password, role)`
- `courses (id, title, description, instructor_id)`
- `enrollments (id, student_id, course_id)`
- `quizzes (id, course_id, title)`
- `questions (id, quiz_id, options, correct_option)`
- `quiz_results (id, quiz_id, student_id, score)`

 Schema file:  
`src/main/resources/schema.sql`

---

##  How to Run

### 1. Requirements
- JDK 25
- IntelliJ IDEA
- MySQL Server
- Maven (bundled)

### 2. Database Setup
```sql
CREATE DATABASE lms_db;
CREATE USER 'lms_user'@'localhost' IDENTIFIED BY 'lms_pass';
GRANT ALL ON lms_db.* TO 'lms_user'@'localhost';
FLUSH PRIVILEGES;
```

##  Run Application

1. Open the project in **IntelliJ IDEA**
2. Go to **Build → Rebuild Project**
3. Run `MainApp.java`
4. Login screen appears
5. Create **seed admin**
6. Proceed using **role-based dashboards** (Admin / Instructor / Student)

---

##  Testing Flow

- **Admin**
  - Create users (Admin / Instructor / Student)
  - Create and view courses

- **Instructor**
  - Create course
  - Create quiz
  - Add quiz questions
  - View quiz results of students

- **Student**
  - Enroll in course
  - Attempt quiz
  - View quiz score

- **Database Verification**
  - Verify records using **MySQL Workbench**

---

##  Project Structure (Simplified)
```
src/main/java/com/lms
 ├── app (Main entry & tests)
 ├── app/gui (Swing dashboards & dialogs)
 ├── web/servlet (Servlet controllers)
 ├── dao & dao/jdbc (DAO layer)
 ├── model (Entities)
 ├── service (Business logic)
 └── util (DB & transaction helpers)

resources/
 └── schema.sql
```

---

##  Conclusion

This **Learning Management System (LMS)** is a **fully functional, stable, and scalable Java application** that demonstrates strong understanding of:

- Object-Oriented Programming (OOP)
- JDBC & Database Integration
- Multithreading & Synchronization
- GUI and Web Application Development
- Clean Architecture and Modular Design

--> The project is **fully documented** in this README for easy understanding, setup, and usage.

---

**Thank you!**  
For any doubts or improvements, feel free to check the code or ask the developer (Aryan Gupta).
