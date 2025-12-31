package com.lms.model;

public class CourseContent {

    private Long id;
    private Long courseId;
    private String fileName;   // PDF / Notes name
    private String filePath;   // server path

    public CourseContent() {}

    public CourseContent(Long courseId, String fileName, String filePath) {
        this.courseId = courseId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
