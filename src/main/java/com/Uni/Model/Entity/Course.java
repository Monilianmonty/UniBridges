package com.Uni.Model.Entity;





public class Course{


    private int courseID;
    private String courseName;
    private int level;


    public Course(String C, int lvl, int cid){
        this.level = lvl;
        this.courseName = C;
        this.courseID = cid;

    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID){
        this.courseID = courseID;
    }




}