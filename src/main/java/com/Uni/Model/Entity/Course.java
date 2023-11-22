package com.Uni.Model.Entity;





public class Course{



    private String courseName;
    private int level;


    public Course(String C, int lvl){
        this.level = lvl;
        this.courseName = C;

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
}