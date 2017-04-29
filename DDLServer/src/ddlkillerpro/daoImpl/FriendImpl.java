package ddlkillerpro.daoImpl;

import ddlkillerpro.bean.FriendSet;
import ddlkillerpro.db.ServerDB;

import java.sql.*;

/**
 * Created by MichelleZhang on 2017/4/12.
 */
public class FriendImpl {
    public Integer setFriend(FriendSet friendSet){
        ServerDB serverDB = new ServerDB();
        int rtpID = 0;
        Connection conn = serverDB.getConnection();
        ResultSet rs = null;
        try{
            String sql = "insert into friendinfo (userID, friendID) values (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, friendSet.getUserID());
            stmt.setInt(2, friendSet.getFriendID());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                rtpID = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        serverDB.closeConnection(conn);
        return rtpID;
    }
}
