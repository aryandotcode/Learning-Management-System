#  Learning Management System (LMS)
**Java GUI + JDBC + OOP + Multithreading Project**  
**Author:** Aryan Gupta (B.Tech 2nd Year)  
**Environment:** IntelliJ IDEA 2025, JDK 25, MySQL Workbench 8  

---

##  Overview
This project is a **Java-based GUI Learning Management System (LMS)** that allows **Admins**, **Instructors**, and **Students** to perform various operations through a desktop interface.  
The project is built with **proper OOP principles**, **Collections**, **Generics**, **DAO architecture**, **JDBC PreparedStatements**, **Transaction Management**, and **Multithreading with Synchronization**.

This README covers setup, features, technical design, and how each Review-1 rubric requirement is fulfilled.

---

#  Features Implemented (Review 1 Requirements)

##  1. Admin Features
- Create Instructor, Student, Admin users  
- Delete users  
- View list of all users  
- View list of all courses  
- **Transaction Demo button** → shows commit/rollback working in DB  
- Prevents duplicate/invalid users

##  2. Instructor Features
- Login using own credentials  
- Create new courses  
- View list of self-created courses  

##  3. Student Features
- Login into student dashboard  
- View all available courses  
- **Persistent Enrollment** into courses (saved in DB)  

---

#  Technical Architecture (Important for Review)

##  OOP Concepts
| OOP Feature | Where Implemented |
|-------------|-------------------|
| **Inheritance** | `User` → `Admin`, `Instructor`, `Student` |
| **Polymorphism** | `openDashboardFor(user)` selects dashboard based on role |
| **Encapsulation** | All model fields private + getters/setters |
| **Interfaces** | `BaseDao<T,ID>`, `EnrollmentDao` |
| **Custom Exception** | `AppException` class |

---

##  Collections & Generics
- `Map<Long, Course>` cache in `CourseService`  
- `List<User>`, `List<Course>`, `List<Enrollment>` using Generics  
- DAO interfaces written with generics:  
  `BaseDao<T,ID>`

---

##  Multithreading & Synchronization
### 1. `AutoSaveThread`  
Background thread that periodically flushes course cache to DB.

### 2. `EnrollmentProcessor`  
Runs multiple concurrent enrollment tasks.

### 3. `ConcurrentEnrollTest.java`  
- Spawns **multiple threads**  
- Demonstrates **synchronized JDBC save**  
- Ensures **no duplicate enrollments** under race conditions  

---

##  JDBC + CRUD + PreparedStatement
**Complete DAO layer implemented with JDBC:**
- `JdbcUserDao`
- `JdbcCourseDao`
- `JdbcEnrollmentDao`

CRUD implemented via:
```java
PreparedStatement ps = conn.prepareStatement(...);
```

No string concatenation → safe against SQL injection.

---

##  Transaction Management
File: `TransactionDemo.java`  
- Inserts User + Course in **single transaction**  
- Forces error → triggers `rollback()`  
- Shown via **Transaction Demo** button in Admin Dashboard  
- Verified in MySQL Workbench (no rows inserted)

---

#  Database Design

## Tables:
### **users**
| id | name | email | password | role |
|----|------|--------|----------|------|

### **courses**
| id | title | description | instructor_id |

### **enrollments**
| id | student_id | course_id | completed |

### Schema file:  
`src/main/resources/schema.sql`

---

#  How to Run

## 1. Requirements
- JDK 25  
- IntelliJ IDEA 2025  
- MySQL Server & Workbench  
- Maven (built-in in IntelliJ)

## 2. Setup DB
Run in MySQL Workbench:

```sql
CREATE DATABASE lms_db;
CREATE USER 'lms_user'@'localhost' IDENTIFIED BY 'lms_pass';
GRANT ALL ON lms_db.* TO 'lms_user'@'localhost';
FLUSH PRIVILEGES;
```

Then run contents of:  
`src/main/resources/schema.sql`

---

## 3. Run Application
1. Open project in IntelliJ  
2. Build → Rebuild Project  
3. Run `MainApp.java`  
4. Login GUI appears  
5. Click **Create Admin (seed)**  
6. Proceed based on role dashboards

---

#  Testing Steps (For Review/Viva Demo)

##  Step 1: Run App → Console prints
```
DB connection OK
```

##  Step 2: Create seed admin
- Click “Create Admin (seed)”

##  Step 3: Admin
- Add Instructor  
- Add Student  
- View Users  
- View Courses  

##  Step 4: Instructor
- Login  
- Create Course  
- Verify in DB:  
```sql
SELECT * FROM courses;
```

##  Step 5: Student
- Login  
- View All Courses  
- Enroll → Persistent save  
- Verify in DB:
```sql
SELECT * FROM enrollments;
```

##  Step 6: Transaction Demo
- In Admin Dashboard click “Transaction Demo”  
- Shows rollback  
- Verify:
```sql
SELECT * FROM users WHERE email='txn_instructor@example.com';
```
(No rows → rollback success)

##  Step 7: Multithreading Test
Run:
`ConcurrentEnrollTest.java`

Expected:
- One success  
- All others fail (unique + sync)  

---

#  Project Structure
```
src/main/java/com/lms
 ├── app
 │    ├── MainApp.java
 │    └── ConcurrentEnrollTest.java
 │
 ├── app/gui
 │    ├── LoginFrame.java
 │    ├── AdminDashboardFrame.java
 │    ├── InstructorDashboardFrame.java
 │    └── StudentDashboardFrame.java
 │
 ├── dao
 │    ├── BaseDao.java
 │    ├── UserDao.java
 │    ├── CourseDao.java
 │    ├── EnrollmentDao.java
 │    └── jdbc/
 │         ├── JdbcUserDao.java
 │         ├── JdbcCourseDao.java
 │         └── JdbcEnrollmentDao.java
 │
 ├── model
 │    ├── User.java
 │    ├── Admin.java
 │    ├── Instructor.java
 │    ├── Student.java
 │    ├── Course.java
 │    └── Enrollment.java
 │
 ├── service
 │    ├── UserService.java
 │    └── CourseService.java
 │
 └── util
      ├── DBConnection.java
      └── TransactionDemo.java

resources/
 └── schema.sql
```

---

#  Conclusion
This project fully equipped with:
- Correct OOP usage  
- Collections & Generics  
- Multithreading + Synchronization  
- JDBC CRUD + PreparedStatement  
- Transaction management (commit/rollback)  
- Functional GUI dashboards for Admin, Instructor & Student  
- Clean DAO architecture with MVC-style separation  


---

**Thank you!**  
For any doubts or improvements, feel free to check the code or ask the developer (Aryan Gupta).
