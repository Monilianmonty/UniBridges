package com.Uni.Model.Entity;
import java.util.List;
import java.util.ArrayList;





public class Student{
    private String name;
    private String email;

    private int studentid;
    private String college;
    private String major;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    private String password;
    private ArrayList<Course> courseList = new ArrayList<>();

    public Student(int studentid, String email, String password) {
        this.studentid = studentid;
        this.email = email;
        this.password = password;
    }



    public void addCourse(Course course){
        this.courseList.add(course);

    }

    public void removeCourse(Course course){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
