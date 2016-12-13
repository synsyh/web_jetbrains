package servlet;

import javaBean.QstnAdder;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunning on 2016/11/28.
 */
@WebServlet(name = "GetQstnServlet")
public class GetQstnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content;
        String[] options;
        String[] answer;
        QstnAdder qstnAdder = null;
        String type = request.getParameter("type");
        switch (type) {
            case "select":
                content = request.getParameter("content");
                options = request.getParameterValues("option");
                answer = request.getParameterValues("answer");
                try {
                    qstnAdder = new QstnAdder(content, options, answer);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "fill":
                content = request.getParameter("content");
                try {
                    qstnAdder = new QstnAdder(content, true);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                break;
            case "judge":
                content = request.getParameter("content");
                answer = request.getParameterValues("selectanswer");
                try {
                    qstnAdder=new QstnAdder(content,answer);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                break;
            case "describe":
                content=request.getParameter("content");
                try {
                    qstnAdder=new QstnAdder(content);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
