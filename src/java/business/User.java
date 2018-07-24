
package business;

public class User {
    private int storeid, password, pwdattempt;
    private String userid, username, adminlevel;
    
    public User() {
        userid = "";
        storeid = 0;
        password = 0;
        pwdattempt = -1;
        adminlevel = "";
    }

    public User(String userid, int pwdattempt) {
        this.userid = userid;
        this.pwdattempt = pwdattempt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPwdattempt() {
        return pwdattempt;
    }

    public void setPwdattempt(int pwdattempt) {
        this.pwdattempt = pwdattempt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminlevel() {
        return adminlevel;
    }

    public void setAdminlevel(String adminlevel) {
        this.adminlevel = adminlevel;
    }
    public boolean isAuthenticated() {
        if (this.password > 0) {
            if (this.password == this.pwdattempt) {
                return true;
            }
        } 
        return false;
    }
}
