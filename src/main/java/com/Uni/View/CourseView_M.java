package com.Uni.View;

import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Student student;

    public CourseView_M(){

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

        populateComboBoxes();

        setVisible(true);



    }



    public List<Course> getCourses() {
        return courses;
    }

    // Method to set a list of courses when they press finish
    public void setCourses(List<Course> courses) {
        this.courses = courses;
        // You can perform additional actions if needed after setting the courses
    }

    private void populateComboBoxes() {
        // Assuming you have a method in your DatabaseStruct class to retrieve courses
        // Replace 'getCoursesFromDatabase()' with the appropriate method in your class

        DatabaseStruct databaseStruct = new DatabaseStruct("jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges", "admin", "staples123");
        List<Course> courses = databaseStruct.getAllCourses();

        // Clear existing items
        CourseBox.removeAllItems();
        LevelBox.removeAllItems();

        // Populate combo boxes with course names and levels
        for (Course course : courses) {
            CourseBox.addItem(course.getCourseName());
            LevelBox.addItem(course.getLevel());
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==FinishB)
        {

            //this.student.setMajor(MajorTF.getText());
            //this.student.addCourse(CourseBox.getSelectedItem(), LevelBox.getSelectedItem() );


            //add course to student object
        }

    }
}
