package servlet;

import javaBean.QstnMtBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunning on 2016/12/18.
 */
@WebServlet(name = "QstnMtServlet")
public class QstnMtServlet extends HttpServlet {
    QstnMtBean qstnMtBean=new QstnMtBean();
    Document doc=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc=qstnMtBean.getExamQstnDoc();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        System.out.println("in xml");
        request.setCharacterEncoding("utf-8");
        //返回xml文档
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
        ServletOutputStream outputStream = response.getOutputStream();
        StringBuffer sb = new StringBuffer();
        String[] strings = doc.asXML().split("\n");
        for (String tmp1 : strings) {
            sb.append(tmp1);
        }

        outputStream.write(sb.toString().getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
