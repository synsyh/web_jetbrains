package javaBean;

import java.sql.*;

import static java.lang.System.out;

/**
 * Created by sunning on 2016/11/28.
 */
public class LoginBean {
    public boolean validate(String username, String userpass) {
        out.println(username);
        out.println(userpass);
//        try {
//            //加载sql数据库
//            Class.forName("com.mysql.jdbc.Driver");
//            String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
//            conn = DriverManager.getConnection(dburl, "root", "");
//            System.out.println("connection built");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName("com.mysql.jdbc.Driver");
            // 数据库连接字符串
            String url = "jdbc:mysql://localhost:3306/xmlexam";
            // 数据库用户名
            String usename = "root";
            // 数据库密码
            String psw = "";
            // 创建Connection连接
            Connection conn = DriverManager.getConnection(url, usename, psw);
            // 判断 数据库连接是否为空
            if (conn != null) {
                String sql = "select * from login_teacher where id='" + username + "' and password='" + userpass + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return true;
                } else {
                    out.print("用户名或密码错误，请重新输入！");
                }
                // 输出连接信息
                //out.println("数据库连接成功！");
                // 关闭数据库连接
                conn.close();
                return false;
            } else {
                // 输出连接信息
                out.println("数据库连接失败！");
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
