package servlet;

import Session.s_session;
import javaBean.user_bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by huozongsheng on 2016/11/29.
 */
public class login extends HttpServlet {
    private String username;
    private String password;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        user_bean userBean = new user_bean();
        username = request.getParameter("username");
        password = request.getParameter("password");
        userBean.setUsername(username);
        userBean.setPassword(password);
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        s_session.setUsername(username);
        System.out.println("登陆："+session.getId());
        System.out.println("用户名:"+userBean.getUsername());
        System.out.println("密码："+userBean.getPassword());

//        if (userBean.checkUser()){
//            request.getRequestDispatcher("Stu_sys.jsp").forward(request, response);
//        }
        if (username.length()==4)
            request.getRequestDispatcher("getclass.jsp").forward(request, response);
        if (username.length()==5)
            request.getRequestDispatcher("Stu_sys.jsp").forward(request, response);
        if (username.length()==3)
            request.getRequestDispatcher("c_check.html").forward(request, response);
//        else request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
