import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Crawler {

    public static void main(String args[]) throws Exception {
        String frontpage = "http://johnhany.net/";
        Connection conn = null;
        //connect the MySQL database
        try {
            //加载sql数据库
            Class.forName("com.mysql.jdbc.Driver");
            String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(dburl, "huo", "sheng0932");
            System.out.println("connection built");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = null;
        String url = frontpage;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        if (conn != null) {
            //create database and tables that will be needed
            try {
                sql = "CREATE DATABASE IF NOT EXISTS crawler";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                sql = "USE crawler";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                sql = "create table if not exists record (recordID int(5) not null auto_increment, URL text , keyword text , crawled tinyint(1), primary key (recordID)) engine=InnoDB DEFAULT CHARSET=utf8";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                sql = "create table if not exists tags (tagnum int(4) not null auto_increment, tagname text not null, primary key (tagnum)) engine=InnoDB DEFAULT CHARSET=utf8";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                sql = "INSERT INTO record (URL, crawled) VALUES ('" + frontpage + "',0)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
