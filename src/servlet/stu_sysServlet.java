package servlet;

import Session.s_session;
import javaBean.AddAswBean;
import javaBean.Examlist;
import javaBean.Login_db;
import javaBean.Stu_info;
import org.dom4j.DocumentException;

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
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by huozongsheng on 2016/12/6.
 */
@WebServlet(name = "stu_sysServlet")
public class stu_sysServlet extends HttpServlet {
    Examlist examlist;
    Stu_info stu_info;
    HttpSession session;
    ArrayList<String[]> exam_detail = new ArrayList<>();
    String[] stu_detail = new String[4];
    ArrayList<String> paper_id;
    ArrayList<String[]> resultlist;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        System.out.println(session.getId());
        if (s_session.exists(session.getId()))
            if (session.getAttribute("token").equals(s_session.getToken(s_session.getUsername(session.getId())))) {
                System.out.println("学生试卷提交成功");
                Enumeration s = request.getParameterNames();
                paper_id = new ArrayList<>();
                resultlist = new ArrayList<>();
                while (s.hasMoreElements()) {
                    paper_id.add(s.nextElement().toString());
                    System.out.println(paper_id.get(0));
                }
                for (int i = 0; i < paper_id.size(); i++) {
                    String[] temp = new String[2];
                    temp[0] = paper_id.get(i);
                    temp[1] = request.getParameter(paper_id.get(i));
                    resultlist.add(temp);
                }
                AddAswBean addAswBean=new AddAswBean(s_session.getExamid(),s_session.getUsername(session.getId()),resultlist);
                try {
                    addAswBean.addAsw();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                s_session.destoryToken(s_session.getUsername(session.getId()));
                Login_db login_db = new Login_db();
                try {
                    login_db.createDB("examxml");
                    login_db.finishExam(s_session.getExamid(), s_session.getUsername(session.getId()));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher("Stu_sys.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("404.html").forward(request, response);
            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("链接到stu_sys成功");
        System.out.println(request.getParameter("name"));
        System.out.println(request.getSession().getId());
        System.out.println(s_session.get_temp_username());
        session = request.getSession();
        s_session.setSession(request.getSession().getId(), s_session.get_temp_username());
        switch (request.getParameter("name")) {
            case "score":
                examscore(request, response);
                break;
            case "examlist":
                examlist(request, response);
                break;
            case "stuinfo":
                stuinfo(request, response);
                break;
        }

    }

    public void stuinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (s_session.exists(request.getSession().getId()) && s_session.getUsername(request.getSession().getId()) != null) {
            System.out.println("进入学生个人信息成功");
            ResultSet resultSet;
            Login_db loginDb = new Login_db();
            try {
                loginDb.createDB("examxml");
                System.out.println("连接数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stu_info = loginDb.getStu_info(s_session.getUsername(request.getSession().getId()));
                System.out.println("获取学生信息成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stu_info.show();
            stu_detail = stu_info.getinfo();
            session.setAttribute("stu_info", stu_detail);
            request.getRequestDispatcher("Stu_information.jsp").forward(request, response);
        } else request.getRequestDispatcher("404.html").forward(request, response);
    }

    public void examlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (s_session.exists(request.getSession().getId()) && s_session.getUsername(request.getSession().getId()) != null) {
            System.out.println("进入examlist成功");
            ResultSet resultSet;
            Login_db loginDb = new Login_db();
            try {
                loginDb.createDB("examxml");
                System.out.println("连接数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                examlist = loginDb.getExamlist(s_session.getUsername(request.getSession().getId()));
                System.out.println("获取考试列表成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            exam_detail = examlist.getExamlist();
            examlist.show();
            session = request.getSession();

            System.out.println("考试列表:" + session.getId());
            session.setAttribute("examlist", exam_detail);
            request.getRequestDispatcher("Examlist.jsp").forward(request, response);
        } else request.getRequestDispatcher("404.html").forward(request, response);
    }

    public void examscore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (s_session.exists(request.getSession().getId()) && s_session.getUsername(request.getSession().getId()) != null) {
            System.out.println("进入examscore成功");
            ResultSet resultSet;
            Login_db loginDb = new Login_db();
            try {
                loginDb.createDB("examxml");
                System.out.println("连接数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                examlist = loginDb.getExamlist(s_session.getUsername(request.getSession().getId()));
                System.out.println("获取成绩单成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            exam_detail = examlist.getExamlist();
            examlist.show();
            session = request.getSession();
            System.out.println("考试列表:" + session.getId());
            session.setAttribute("examlist", exam_detail);
            request.getRequestDispatcher("Stu_grade.jsp").forward(request, response);
        } else request.getRequestDispatcher("404.html").forward(request, response);
    }
}