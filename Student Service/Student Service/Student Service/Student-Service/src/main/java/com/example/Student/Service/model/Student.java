package com.example.Student.Service.model;

public class Student {

    private Long studentId; // Mã số sinh viên
    private String fullName; // Họ tên
    private String dateOfBirth; // Ngày sinh, dạng "yyyy-MM-dd"
    private String gender; // Giới tính
    private String hometown; // Quê quán
    private String major; // Ngành học

    public Student() {
    }

    public Student(Long studentId, String fullName, String dateOfBirth, String gender, String hometown, String major) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hometown = hometown;
        this.major = major;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}