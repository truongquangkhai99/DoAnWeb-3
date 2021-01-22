package controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import beans.User;
import models.UserModel;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "AccountServlet",urlPatterns="/Account/*")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path){
            case "/Register":
                AddUser(request);
                ServletUtils.redirect("/Account/Login",request,response);
                break;
            case"/Login":
                postLogin(request,response);
                break;

        }
    }
    private  void postLogin(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = UserModel.FindByUserName(username);
        if(user.isPresent()){
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),user.get().getPassword());
            if (result.verified){
                HttpSession session = request.getSession();
                session.setAttribute("auth",true);
                session.setAttribute("authUser",user.get());
                ServletUtils.redirect("/Home/Index",request,response);
            }
            else {
                request.setAttribute("hasError",true);
                request.setAttribute("errorMessage","Invalid Password");
                ServletUtils.forward("/views/vwAccount/login.jsp",request,response);
            }

        }
        else {
            request.setAttribute("hasError",true);
            request.setAttribute("errorMessage","Invalid username");
            ServletUtils.forward("/views/vwAccount/login.jsp",request,response);
        }


    }
    private void AddUser(HttpServletRequest request){
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
       String name = request.getParameter("name");
       String email = request.getParameter("email");
       String office = request.getParameter("office");
        UserModel.Add(new User(-1,username,bcryptHashString,name,email,office));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path){
            case "/Register":

                ServletUtils.forward("/views/vwAccount/register.jsp",request,response);
                break;
            case "/Login":
                request.setAttribute("hasError",false);
                ServletUtils.forward("/views/vwAccount/login.jsp",request,response);
                break;
            case"/IsExistUsername":
                String username = request.getParameter("username");
                Optional<User> user = UserModel.FindByUserName(username);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                out.print(user.isPresent());
                out.flush();
                break;
            case"/IsExistEmail":
                String email = request.getParameter("email");
                Optional<User> userE = UserModel.FindByEmail(email);
                PrintWriter outp = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                outp.print(userE.isPresent());
                outp.flush();
                break;
            default:
                ServletUtils.redirect("/notFound",request,response);
        }

    }
}