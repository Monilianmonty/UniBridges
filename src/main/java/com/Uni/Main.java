package com.Uni;

import com.Uni.Controller.Controller;
import com.Uni.Controller.Controller_J;
import com.Uni.View.*;

public class Main {

    public static void main(String args[]) {
        Login_View logView = new Login_View();
        CreateUser_View userView = new CreateUser_View();
        //Hub hubView = new Hub();
        CourseView_M courseView = new CourseView_M();

        //Monty's controller
        Controller Controller = new Controller(logView, userView, courseView);

        //Joe's controller
        //Controller_J Controller = new Controller_J(logView, userView);

        Controller.initController();

    }




}
