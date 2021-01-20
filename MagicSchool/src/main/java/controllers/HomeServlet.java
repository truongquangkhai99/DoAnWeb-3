package controllers;

import beans.Course;
import beans.Evaluate;
import beans.User;
import models.CourseModel;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/Home/*")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if(path == null || path.equals("/")){
            path="/Index";
        }
        switch (path){
            case "/Index":
                List<Course> list = CourseModel.getAll();
                request.setAttribute("course",list);

                List<User> listNameTeacher = CourseModel.getTeach();
                request.setAttribute("users",listNameTeacher);

                List<Evaluate> listPoint = CourseModel.getPoint();
                request.setAttribute("evaluate",listPoint);


                ServletUtils.forward("/views/vwHome/Index.jsp",request,response);
                break;
            default:
                ServletUtils.redirect("/NotFound",request,response);
                break;
        }
    }
}
