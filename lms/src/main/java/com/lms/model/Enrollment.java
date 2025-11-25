package com.lms.model;

public class Enrollment {
    private Long id;
    private Long studentId;
    private Long courseId;
    private boolean completed;

    public Enrollment() {}

    public Enrollment(Long id, Long studentId, Long courseId, boolean completed) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.completed = completed;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
