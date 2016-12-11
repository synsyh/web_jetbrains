package javaBean;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by huozongsheng on 2016/12/5.
 */
public class Examlist
{
    private ArrayList<String[]> examlist;
    public Examlist(){
       examlist = new ArrayList<>();
    }
    public void addlist(int class_id, String class_name, int score, String date){
        HttpSession httpSession;
        String[] temp = new String[5];
        temp[0] = String.valueOf(class_id);
        temp[1] = class_name;
        temp[2] = String.valueOf(score);
        temp[3] = date;
        examlist.add(temp);
    }
    public int size(){return this.examlist.size(); }
    public ArrayList<String[]> getExamlist(){return this.examlist;}
    public void show(){
        for (int i=0; i<examlist.size(); i++)
        System.out.println(examlist.get(i)[0]+examlist.get(i)[1]+examlist.get(i)[2]+examlist.get(i)[3]);
    }

}
