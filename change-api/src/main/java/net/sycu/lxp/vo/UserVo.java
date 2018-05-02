package net.sycu.lxp.vo;

public class UserVo extends PageVo {

    private String id;

    private String account;//用户账号

    private String password;//密码

    private String userName;//用户姓名

    private String userType;//用户类型，0：系统管理员，1：系统用户，2：普通用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
