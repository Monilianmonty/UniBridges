package com.Uni.View;

import com.Uni.Controller.Controller;
import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CourseView_M  extends JFrame implements ActionListener {

    private JFrame frame = new JFrame("Course View");
    private JComboBox<String> CourseBox;
    private JComboBox<Integer> LevelBox;
    private JLabel Course_page;
    private JLabel Major;
    private JTextField MajorTF;
    private JButton FinishB;
    private JButton addCourse;
    private List<Course> courses;
    private Student currentStudent;

    private Course course;

    private Controller controller;

    private JTextArea courseTextArea;


    public CourseView_M(Controller controller, Student currentStudent){
        this.controller = controller;
        this.currentStudent = currentStudent;


        initComp();
        populateComboBoxes();


    }

    public CourseView_M(){

    }


    public void initComp(){

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Basic courses for the course box
        CourseBox= new JComboBox<String>();
        CourseBox.setBounds(100, 110, 150, 30);

        CourseBox.setName("select a course");
        /*
        CourseBox.addItem("Math");
        CourseBox.addItem("Science");
        CourseBox.addItem("History");
        CourseBox.addItem("Art");
        CourseBox.addItem("Engineering");
        CourseBox.addItem("Computer Science");
        CourseBox.addItem("Software Engineering");

         */


        add(CourseBox);

        //course levels
        LevelBox = new JComboBox<Integer>();
        LevelBox.setBounds(300, 110, 100, 30);
        LevelBox.setName("select a level");

        LevelBox.addItem(100);
        LevelBox.addItem(200);
        LevelBox.addItem(300);
        LevelBox.addItem(400);
        add(LevelBox);

        //intro text
        Course_page = new JLabel("Add your courses");
        Course_page.setBounds(200, 50, 100, 20);
        add(Course_page);

        //finish button
        FinishB = new JButton("Finish");
        FinishB.setBounds(300, 150, 100, 30);
        add(FinishB);

        //add course button
        addCourse = new JButton("Add Course");
        addCourse.setBounds(100,150, 100, 30);
        add(addCourse);

        //enter major label and button
        Major = new JLabel("Enter Major:");
        Major.setBounds(100, 75, 100, 30);
        add(Major);

        //enter major label and button
        MajorTF = new JTextField("Major");
        MajorTF.setBounds(300, 75, 100, 30);
        add(MajorTF);

        // Add this code in the initComp method
        courseTextArea = new JTextArea();
        courseTextArea.setBounds(100, 190, 300, 100);
        add(courseTextArea);




        setVisible(true);



    }



    public List<Course> getCourses() {
        return courses;
    }

    // Method to set a list of courses when they press finish
    public List<Course> appendCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>(); // Initialize the list if it's null
        }
        courses.add(course);

        return courses; // Return the updated list
    }

    // Method to set a list of courses when they press finish
    public void setCourses(List<Course> courses) {
        this.courses = courses;
        // You can perform additional actions if needed after setting the courses
    }

    public Course getCourse(){
        return this.course;
    }

    private void populateComboBoxes() {
        // Assuming you have a method in your DatabaseStruct class to retrieve courses
        // Replace 'getCoursesFromDatabase()' with the appropriate method in your class

        DatabaseStruct databaseStruct = new DatabaseStruct("jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges", "admin", "staples123");
        List<Course> courses = databaseStruct.getAllCourses();

        // Clear existing items
        CourseBox.removeAllItems();
        LevelBox.removeAllItems();
        int n = 100;
        // Populate combo boxes with course names and levels
        for (Course course : courses) {
            CourseBox.addItem(course.getCourseName());



            LevelBox.addItem(n);
            n += 100;
            if(n >=400) {
                LevelBox.addItem(null);
            }

        }
    }


    public String getSelectedCourseAndLevel() {
        String selectedCourse = (String) CourseBox.getSelectedItem();
        System.out.println(selectedCourse);
        Integer selectedLevel = (Integer) LevelBox.getSelectedItem();

        System.out.println(selectedLevel);
        if (selectedCourse != null && selectedLevel != null) {
            return selectedCourse + " - Level " + selectedLevel;
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {



    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JComboBox<String> getCourseBox() {
        return CourseBox;
    }

    public void setCourseBox(JComboBox<String> courseBox) {
        CourseBox = courseBox;
    }

    public JComboBox<Integer> getLevelBox() {
        return LevelBox;
    }

    public void setLevelBox(JComboBox<Integer> levelBox) {
        LevelBox = levelBox;
    }

    public JLabel getCourse_page() {
        return Course_page;
    }

    public void setCourse_page(JLabel course_page) {
        Course_page = course_page;
    }

    public JLabel getMajor() {
        return Major;
    }

    public void setMajor(JLabel major) {
        Major = major;
    }

    public JTextField getMajorTF() {
        return MajorTF;
    }

    public void setMajorTF(JTextField majorTF) {
        MajorTF = majorTF;
    }

    public JButton getFinishB() {
        return FinishB;
    }

    public void setFinishB(JButton finishB) {
        FinishB = finishB;
    }

    public JButton getAddCourse() {
        return addCourse;
    }

    public void setAddCourse(JButton addCourse) {
        this.addCourse = addCourse;
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

    public JTextArea getCourseTextArea() {
        return courseTextArea;
    }

    public void setCourseTextArea(JTextArea courseTextArea) {
        this.courseTextArea = courseTextArea;
    }
}
