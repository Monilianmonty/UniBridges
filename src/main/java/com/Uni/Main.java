package com.Uni;

import com.Uni.Unibridges.Controller.Controller;
import com.Uni.View.*;

import java.sql.SQLException;

public class Main {

    public static void main(String args[]) throws SQLException {
        Login_View logView = new Login_View();
        CreateUser_View userView = new CreateUser_View();
        //Hub hubView = new Hub();
        CourseView_M courseView = new CourseView_M();
        CourseChat1_M CCview = new CourseChat1_M();

        //Monty's controller
        Controller controller = new Controller(logView, userView, courseView, CCview);

        controller.CCview = CCview;
        //Joe's controller
        //Controller_J Controller = new Controller_J(logView, userView);

        controller.initController();

    }




}
