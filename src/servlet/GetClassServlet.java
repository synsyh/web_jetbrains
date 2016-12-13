package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by sunning on 2016/12/5.
 */
@WebServlet(name = "GetClassServlet")
public class GetClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String className = request.getParameter("className");
        String studentNum = request.getParameter("studentNum");
        Connection conn = null;

        //connect the MySQL database

        try {
            //加载sql数据库
            Class.forName("com.mysql.jdbc.Driver");
            String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(dburl, "root", "");
            System.out.println("connection built");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        int count = 0;

        if (conn != null) {
            //create database and tables that will be needed
            try {
                sql = "USE examxml";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                sql = "SELECT MAX(id) AS id FROM `tea1001` WHERE 1";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt("id") + 1;
                }
                sql = "INSERT INTO tea1001 (id, class_name,num,status) VALUES ('" + (id) + "','" + className + "','" + studentNum + "',0)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("done");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
