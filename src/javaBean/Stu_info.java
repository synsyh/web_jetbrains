package javaBean;

import java.util.ArrayList;

/**
 * Created by huozongsheng on 2016/12/5.
 */
public class Stu_info {
    String [] stu_info;
    public Stu_info(){stu_info = new String[4];}
    public void addinfo(int stuid, String stuname, int age, String place){
        String[] temp = new String[4];
        stu_info[0] = String.valueOf(stuid);
        stu_info[1] = stuname;
        stu_info[2] = String.valueOf(age);
        stu_info[3] = place;

    }
    public String[] getinfo(){return stu_info;}
    public void show(){
        System.out.println(stu_info[0]+stu_info[1]+stu_info[2]+stu_info[3]);
    }
}
