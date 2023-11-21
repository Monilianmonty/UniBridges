package com.Uni.View;

import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseView extends JFrame implements ActionListener
{
    private JFrame frame = new JFrame("Course View");
    private JComboBox<String> CourseBox;
    private JComboBox<Integer> LevelBox;
    private JLabel Course_page;
    private JLabel Major;
    private JTextField MajorTF;
    private JButton FinishB;
    private List<Course> courses;
    private Student student;

    public CourseView(){

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Basic courses for the course box
        CourseBox.addItem("Math");
        CourseBox.addItem("Science");
        CourseBox.addItem("History");
        CourseBox.addItem("Art");
        CourseBox.addItem("Engineering");
        CourseBox.addItem("Computer Science");
        CourseBox.addItem("Software Engineering");




    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
