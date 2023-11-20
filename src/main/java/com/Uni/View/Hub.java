package com.Uni.View;

import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hub extends JFrame implements ActionListener {

    Course course[] = new Course[10]; //set equal to database student
    Student student; //set equal to database student

    JFrame frame = new  JFrame("HUB");
    JButton exitB, Classes[];

    public Hub()
    {
        setTitle("HUB");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        int x = 20;


        for(int i=0;course[i]!=null;i++) //individual courses
        {
            Classes[i] = new JButton("  "); //Text filled with course name in database
            Classes[i].setBounds(20, 20+x, 50, 20);
            add(Classes[i]);
            x+=20;
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitB)
        {
            //return to login
        }

        else {
            for (int i=0;course[i]!=null;i++)
            {
                if(e.getSource()==course[i])
                {
                    //send user to chat room
                }
            }
        }



    }
}



