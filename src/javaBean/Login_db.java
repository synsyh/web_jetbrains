package javaBean;

import java.sql.*;

/**
 * Created by huozongsheng on 2016/12/4.
 */
public class Login_db {
    Connection conn = null;
    Statement statement = null;
    String sql = null;
    Examlist examlist;
    Stu_info stu_info;
    public Login_db(){
        try {
            //加载sql数据库
            Class.forName("com.mysql.jdbc.Driver");
            String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(dburl, "root", "");
            System.out.println("连接建立");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        examlist = new Examlist();
        stu_info = new Stu_info();
    }
    public void createDB(String name) throws SQLException {
        sql = "CREATE DATABASE IF NOT EXISTS "+name;
        statement = conn.createStatement();
        statement.executeUpdate(sql);

        sql = "USE "+name;
        statement = conn.createStatement();
        statement.executeUpdate(sql);
    }
    public void createTable(String sql) throws SQLException {
        this.sql = sql;
        statement = conn.createStatement();
        statement.executeUpdate(this.sql);
    }
    public ResultSet getRes(String query) throws SQLException {
        statement = conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    public Examlist getExamlist(String stuID) throws SQLException {
        statement = conn.createStatement();
        ResultSet rs = null;
        sql = "SELECT * FROM stu"+stuID;
        rs = statement.executeQuery(sql);
        while(rs.next()){
            //rs.get+数据库中对应的类型+(数据库中对应的列别名)
            int id = rs.getInt("class_id");
            String name = rs.getString("class_name");
            int score = rs.getInt("score");
            String examdate = rs.getString("exam_date");
            examlist.addlist(id,name,score,examdate);
        }
//        examlist.show();
        return examlist;
    }
    public Stu_info getStu_info(String stuID) throws SQLException {
        statement = conn.createStatement();
        ResultSet rs = null;
        sql = "SELECT * FROM login_student WHERE id ="+stuID;
        rs = statement.executeQuery(sql);
        while(rs.next()){
            //rs.get+数据库中对应的类型+(数据库中对应的列别名)
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String place = rs.getString("place");
            int id = rs.getInt("id");
            stu_info.addinfo(id,name,age,place);
        }
        stu_info.show();
        return stu_info;
    }
    public void finishExam(String examID,String stuID) throws SQLException {
        statement = conn.createStatement();
        sql = "UPDATE stu"+stuID+" SET score=-1 WHERE class_id="+examID;
        System.out.println(sql);
        statement.executeUpdate(sql);
    }
}
