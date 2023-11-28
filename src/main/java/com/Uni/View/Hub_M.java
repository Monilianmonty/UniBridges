package com.Uni.View;

import com.Uni.Controller.Controller;
import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Hub_M extends JFrame implements ActionListener {

    Course course[] = new Course[10]; //set equal to database student


    JFrame frame = new  JFrame("HUB");
    JButton exitB, Classes[];

    private Controller controller;

    private JTextField usernameTB;



    private List<JButton> courseButtons = new ArrayList<>();

    private JPanel coursePanel;
    private Student currentStudent;

    private JButton courseButton;

    private List<Course> courses;
    public Hub_M(Controller controller, Student currentStudent, List<Course> courses ){
        this.controller = controller;
        this.currentStudent = currentStudent;
        this.courses = courses;

        //show the hub
        initcompH();

        //spawn the courses
        spawnCoursesH(courses);

    }

    public Hub_M(){

    }


    public void initcompH() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);

        coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameTB = new JTextField("Username", 10);

        add(usernameTB);




        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {



    }

    //make course buttons based on a list based on current students classes
    public void spawnCoursesH(List<Course> courses){

        int y = 20;




        for (Course course : courses) {
            courseButton = new JButton(course.getCourseName());
            courseButton.setBounds(20, y, 150, 30); // Adjust the bounds as needed
            add(courseButton);
            courseButtons.add(courseButton);

            y += 40; // Adjust the vertical spacing between buttons
        }

        setCourseButtons(courseButtons);

    }
    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }



    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getExitB() {
        return exitB;
    }

    public void setExitB(JButton exitB) {
        this.exitB = exitB;
    }

    public JButton[] getClasses() {
        return Classes;
    }

    public void setClasses(JButton[] classes) {
        Classes = classes;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public JTextField getUsernameTB() {
        return usernameTB;
    }

    public void setUsernameTB(String usernameTB) {
        this.usernameTB.setText(usernameTB);
    }

    public List<JButton> getCourseButtons() {
        return courseButtons;
    }

    public void setCourseButtons(List<JButton> courseButtons) {
        this.courseButtons = courseButtons;
    }

    public JButton getCourseButton(){
        return courseButton;
    }

    public void setCourseButton(JButton courseButton){
        this.courseButton = courseButton;
    }

}
