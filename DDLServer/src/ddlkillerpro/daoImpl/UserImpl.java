package ddlkillerpro.daoImpl;

/**
 * Created by MichelleZhang on 2017/4/12.
 */

import ddlkillerpro.db.ServerDB;
import ddlkillerpro.bean.UserSet;


import java.sql.*;

public class UserImpl {
    public UserSet login(Integer userID, String userPwd){
        UserSet retUser = new UserSet();
        ServerDB serverDB = new ServerDB();
        ResultSet rs = null;
        Connection conn = serverDB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from userinfo where userID=? and userPwd=?");
            stmt.setInt(1, userID);
            stmt.setString(2,userPwd);
            rs = stmt.executeQuery();
            if(rs.next()){
                retUser.setUserID(Integer.valueOf(rs.getString(1)));
                retUser.setUserImg(rs.getString(2));
                retUser.setUserPwd(rs.getString(3));
                retUser.setUserName(rs.getString(4));
            }
            else {
                retUser = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        serverDB.closeConnection(conn);
        return retUser;
    }

    public UserSet setUser(UserSet user){
        UserSet retUser = new UserSet();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            String sql = "Update userinfo set userImg = ?, userPwd = ?, userName = ? where userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserImg());
            stmt.setString(2, user.getUserPwd());
            stmt.setString(3, user.getUserName());
            stmt.setInt(4, user.getUserID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retUser = user;
            }
            else {
                retUser = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retUser;
    }

    public UserSet register(UserSet user){
        UserSet retUser = new UserSet();
        ServerDB serverDB = new ServerDB();
        int userID = 0;
        ResultSet rs = null;
        Connection conn = serverDB.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement("insert into userinfo (userName, userPwd, userImg) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPwd());
            stmt.setString(3, user.getUserImg());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                userID = rs.getInt(1);
                user.setUserID(userID);
                retUser = user;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return retUser;
    }

}
