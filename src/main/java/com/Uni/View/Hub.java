package com.Uni.View;

import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hub extends JFrame
{
Course course[] = new Course[10];
Student student;

JFrame frame = new JFrame("HUB");
JButton exitB = new JButton("EXIT HUB");

public Hub() {


    ActionListener actionListenerEXIT = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {




        }
    };


    exitB.addActionListener(actionListenerEXIT);




};


}
