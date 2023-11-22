package com.Uni;

import com.Uni.Controller.Controller;
import com.Uni.Controller.Controller_J;
import com.Uni.View.CourseView;
import com.Uni.View.CreateUser_View;
import com.Uni.View.Hub;
import com.Uni.View.Login_View;

public class Main {

    public static void main(String args[]) {
        Login_View logView = new Login_View();
        CreateUser_View userView = new CreateUser_View();
        //Hub hubView = new Hub();
        //CourseView courseView = new CourseView();

        //Monty's controller
        Controller Controller = new Controller(logView, userView);

        //Joe's controller
        //Controller_J Controller = new Controller_J(logView, userView);

        Controller.initController();

    }




}
