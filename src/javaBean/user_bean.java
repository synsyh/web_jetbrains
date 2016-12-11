package javaBean;

/**
 * Created by huozongsheng on 2016/11/29.
 */
public class user_bean {
    private String username = "";
    private String password = "";
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkUser(){
        if (this.username.equals("10001")&&this.password.equals("10001"))
            return true;
        else
            return false;
    }
    public user_bean() {
    }
}
