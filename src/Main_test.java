import Session.s_session;
import javaBean.Login_db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by huozongsheng on 2016/12/4.
 */
public class Main_test {
    public static void main(String [] args) throws SQLException {
        ResultSet resultSet ;
        Login_db loginDb = new Login_db();
        loginDb.createDB("examxml");
//        loginDb.createTable("create table if not exists student (ID int(11) not null , name text , keyword text , status int , primary key (ID)) engine=InnoDB DEFAULT CHARSET=utf8");
//        resultSet = loginDb.getRes("select ID from student");
//        System.out.println();
//        loginDb.getExamlist("10001");
        loginDb.getStu_info("10005");
        loginDb.finishExam("2014002","10001");

    }
}
