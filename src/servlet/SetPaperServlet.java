package servlet;

import javaBean.SetPaperBean;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunning on 2016/12/3.
 */
@WebServlet(name = "SetPaperServlet")
public class SetPaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selallmark, selmark;
        String filallmark, filmark;
        String desallmark, desmark;
        String judallmark, judmark;
        String classname=request.getParameter("classname");
        selallmark = request.getParameter("selallmark");
        selmark = request.getParameter("selmark");
        filallmark = request.getParameter("filallmark");
        filmark = request.getParameter("filmark");
        desallmark = request.getParameter("desallmark");
        desmark = request.getParameter("desmark");
        judallmark = request.getParameter("judallmark");
        judmark = request.getParameter("judmark");
        try {
            SetPaperBean setPaperBean = new SetPaperBean(classname, selallmark, selmark, filallmark, filmark, desallmark, desmark, judallmark, judmark);
            setPaperBean.saveTactics();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
