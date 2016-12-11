package servlet;

import javaBean.Examlist;
import javaBean.Login_db;
import Session.s_session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by huozongsheng on 2016/12/5.
 */
@WebServlet(name = "examlistServlet")
public class examlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        System.out.println(exam_detail.get(i)[0] + exam_detail.get(i)[1] + exam_detail.get(i)[2] + exam_detail.get(i)[3]);
//        request.setAttribute(examlist);
    }
}
