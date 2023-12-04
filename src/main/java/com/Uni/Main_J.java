package com.Uni;

import com.Uni.Unibridges.Controller.Controller_J;
import com.Uni.View.CourseView;

public class Main_J
{
    public static void main(String args[]) {
        //Login_View logView = new Login_View();
        //CreateUser_View userView = new CreateUser_View();
        //Hub hubView = new Hub();
        CourseView courseView = new CourseView();



        //Joe's controller
        Controller_J Controller = new Controller_J( courseView );

        Controller.initController();

    }




}
