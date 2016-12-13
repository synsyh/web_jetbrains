package servlet;

import Session.s_session;
import javaBean.SetPaperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by huozongsheng on 2016/12/5.
 */
@WebServlet(name = "paperServlet")
public class paperServlet extends HttpServlet {
    static public Object lock = new Object();
    HttpSession session;
    String clsNum;
    String clsUrl;
    private String separator = File.separator;
    SetPaperBean setPaperBean = new SetPaperBean();
    Document doc = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc = setPaperBean.getFile();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println("in xml");
        request.setCharacterEncoding("utf-8");
//        String name="testname";
//        String pwd="testpwd";
        //返回xml文档
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
        ServletOutputStream outputStream = response.getOutputStream();
        StringBuffer sb = new StringBuffer();
        String[] strings = doc.asXML().split("\n");
        for (String tmp1 : strings) {
            sb.append(tmp1);
        }
//        sb.append("<?xml version='1.0' encoding='utf-8'?>");
//        sb.append("<user><name>"+name+"</name><pwd>"+pwd+"</pwd></user>");

        outputStream.write(sb.toString().getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        System.out.println("paperServler链接成功");
        clsNum = request.getParameter("name");

        clsUrl = "/Users/sunning/Documents/web_jetbrains/web/" + "cls" + clsNum + ".xml";
        try {
            setPaperBean.readTactics(clsUrl);
            setPaperBean.setPaper(clsNum, s_session.getUsername(session.getId()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if (s_session.exists(session.getId())) {
            System.out.println(s_session.getUsername(session.getId()));
            System.out.println("试卷：" + session.getId());
            System.out.println("待设置xml动态生成的路径");
            try {
                session.setAttribute("file", setPaperBean.getFile());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
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
