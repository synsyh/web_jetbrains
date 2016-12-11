package Session;

import java.util.ArrayList;

/**
 * Created by huozongsheng on 2016/12/6.
 */
public  class s_session {
    static ArrayList<String[]> sessionID = new ArrayList<>();
    static String password = null;
    static String username = null;
    static double token = 0.0;
    static ArrayList<String[]> tokens = new ArrayList<>();
    static String Examid=null;
    static ArrayList<String[]> times = new ArrayList<>();
    static public Object lock = new Object();
    public static void setExamid (String examid){Examid = examid;}
    public static String getExamid(){return Examid;}
    public static void setUsername(String name){username = name;}
    public static String get_temp_username(){return username;}
    public static void setToken (String usrname){
        String[] temp = new String[2];
        temp[0] = usrname;
        token = Math.random();
        temp[1] = String.valueOf(token);
        tokens.add(temp);
        System.out.println(token);
        autoCount(usrname);
    }
    public static double getToken (String usrname){
        for (int i=0; i<tokens.size(); i++)
        {
            if (tokens.get(i)[0].equals(usrname))
                token = Double.valueOf(tokens.get(i)[1]);
            else token = 0;
        }
        return token;}
    public static void destoryToken(String usrname){
        String[] temp = new String[2];
        for (int i=0; i<tokens.size(); i++){
            if (tokens.get(i)[0].equals(usrname)){
                temp[0] = usrname; temp[1] = "0";
                tokens.set(i,temp);
            }
        }
        token=0.0;}
    public static void setSession(String id, String username){
        String[] temp = new String[2];
        temp[0] = id;
        temp[1] = username;
        sessionID.add(temp);
    }
    public static boolean exists(String id){
        for (int i=0; i<sessionID.size(); i++){
            if (sessionID.get(i)[0].equals(id))
                return true;
        }
        return false;}
    public static String getUsername(String id){
        for (int i=0; i<sessionID.size(); i++){
            if (sessionID.get(i)[0].equals(id))
                return sessionID.get(i)[1];
        }
        return null;}
    public static int getTime(String name) throws InterruptedException {
        synchronized (lock){
            lock.wait(1000);
        int temp = 0;
        for (int i=0; i<times.size(); i++){
            System.out.println("执行循环");
            if (times.get(i)[0].equals(name)){
                temp= Integer.valueOf(times.get(i)[1]);
            }
                }
        return temp;}
    }
    public static void setTime(String name, int count){
        synchronized (lock){
        String[] temp = new String[2];
        boolean t = false;
        temp[0] = name;
        temp[1] = String.valueOf(count);
        for (int i=0; i<times.size(); i++)
            if (times.get(i)[0].equals(name))
            {
                times.set(i,temp);
                t = true;
                break;
            }
        if (!t)
            times.add(temp);

        System.out.println("设置时间"+times.get(0)[1]);
        lock.notify();
        }
    }
    public static void autoCount(String usrname){
        new Thread(() -> {
            int count = 6000;  //一百分钟
            while (count!=0){
                try {
                    setTime(usrname,count);
                    Thread.sleep(1000);
                    count--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            destoryToken(usrname);
        }).start();
    }
}
