package com.example.michellezhang.ddlkillerpro.database;

import org.litepal.crud.DataSupport;

/**
 * Created by zdl on 2017/4/16.
 */

public class UserInfo extends DataSupport {
    private int id;
    private int userid;
    private String userName;
    private String userPwd;
    private String userImg;

    public UserInfo(int userid, String userName, String userPwd, String userImg){
        this.userid = userid;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userImg = userImg;
    }

    public UserInfo(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }


    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
