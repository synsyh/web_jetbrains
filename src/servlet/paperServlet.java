package servlet;

import Session.s_session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by huozongsheng on 2016/12/5.
 */
@WebServlet(name = "paperServlet")
public class paperServlet extends HttpServlet {
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("paperServler链接成功");
        System.out.println(request.getParameter("name"));
        session = request.getSession();
        if (s_session.exists(session.getId())) {
            System.out.println(s_session.getUsername(session.getId()));
            System.out.println("试卷：" + session.getId());
            System.out.println("待设置xml动态生成的路径");
            session.setAttribute("link","number.xml");
            System.out.println(s_session.getToken(s_session.getUsername(session.getId())));
            if (s_session.getToken(s_session.getUsername(session.getId())) == 0.0) {
                s_session.setToken(s_session.getUsername(session.getId()));
                session.setAttribute("token", s_session.getToken(s_session.getUsername(session.getId())));
                s_session.setExamid(request.getParameter("name"));
            }
            System.out.println(s_session.getExamid());
            try {
                session.setAttribute("time", s_session.getTime(s_session.getUsername(session.getId())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("paper.jsp").forward(request, response);
        }
    }
}