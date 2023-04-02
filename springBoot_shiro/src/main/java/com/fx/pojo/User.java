package com.fx.pojo;

public class User {
    private Integer uid;
    private String account;
    private String uname;
    private String pwd;

    public User() {
    }

    public User(Integer uid, String uname, String pwd, String account) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.account = account;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account=" + account +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
