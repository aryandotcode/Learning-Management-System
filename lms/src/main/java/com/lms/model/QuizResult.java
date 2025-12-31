package com.lms.model;

public class QuizResult {

    private Long id;
    private Long quizId;
    private Long studentId;
    private int score;

    public QuizResult() {
    }

    public QuizResult(Long quizId, Long studentId, int score) {
        this.quizId = quizId;
        this.studentId = studentId;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
