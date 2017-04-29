package com.example.michellezhang.ddlkillerpro.database.impl;

import com.example.michellezhang.ddlkillerpro.database.UserInfo;

import org.litepal.crud.DataSupport;

/**
 * Created by zdl on 2017/4/17.
 */

public class UserImpl {
    public void setUser(int userid, String userName, String userPwd, String userImg) {
        UserInfo UserInfo = new UserInfo();
        UserInfo.setUserid(userid);
        UserInfo.setUserName(userName);
        UserInfo.setUserPwd(userPwd);
        UserInfo.setUserImg(userImg);
        UserInfo.save();
    }

    public boolean login(int id, String userPwd) {
        UserInfo userInfo =DataSupport.find(UserInfo.class,id);
        if(userInfo.getUserPwd()==userPwd)
            return true;
        else
            return  false;
    }

    public void addUser(UserInfo userInfo){
        DataSupport.deleteAll(UserInfo.class);
        UserInfo tmpUser = userInfo;
        tmpUser.setId(2);
        tmpUser.save();
    }

    public static Integer getUserID(){
        UserInfo userInfo = DataSupport.findLast(UserInfo.class, true);
        return userInfo.getUserid();
    }

}
