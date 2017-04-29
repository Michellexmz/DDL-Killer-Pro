package ddlkillerpro.daoImpl;

/**
 * Created by MichelleZhang on 2017/4/12.
 */

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.db.ServerDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemImpl {
    //添加事项，需要的属性为itemName, userID, labelID, itemNewTime,itemDeadline, showable, noticeFlag, groupFlag
    //返回值为ItemSet的对象,注意该对象itemCompleted为null
    public ItemSet addItem(ItemSet itemSet){
        ItemSet retItem = new ItemSet();
        int itemID = 0;
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        ResultSet rs = null;
        try{
            Timestamp timestamp = new Timestamp(itemSet.getItemDeadline().getTime());
            String sql = "insert into iteminfo (itemName, userID, labelID, itemNewTime," +
                    "itemDeadline, showable, noticeFlag, groupFlag) values ( ?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, itemSet.getItemName());
            stmt.setInt(2, itemSet.getUserID());
            stmt.setInt(3, itemSet.getLabelID());
            stmt.setTimestamp(4, itemSet.getItemNewTime());
            stmt.setTimestamp(5, timestamp);
            stmt.setInt(6, itemSet.isShowable());
            stmt.setInt(7, itemSet.isNoticeFlag());
            stmt.setInt(8, itemSet.isGroupFlag());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                itemID = rs.getInt(1);
                itemSet.setItemID(itemID);
                retItem = itemSet;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        serverDB.closeConnection(conn);
        return retItem;
    }

    //修改事项属性，需要的属性为itemName，labelID,itemDeadline,showable,noticeFlag,groupFlag,itemID
    //注意！！！传进去的itemSet应该为包含了所有（十个）属性的对象
    public ItemSet setItem(ItemSet itemSet){
        ItemSet retItem = new ItemSet();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            Timestamp timestamp = new Timestamp(itemSet.getItemDeadline().getTime());
            String sql = "update iteminfo set itemName=?, labelID=?, itemDeadline=?, showable=?," +
                    " noticeFlag=?, groupFlag=? where itemID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemSet.getItemName());
            stmt.setInt(2, itemSet.getLabelID());
            stmt.setTimestamp(3, timestamp);
            stmt.setInt(4, itemSet.isShowable());
            stmt.setInt(5, itemSet.isNoticeFlag());
            stmt.setInt(6, itemSet.isGroupFlag());
            stmt.setInt(7, itemSet.getItemID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retItem = itemSet;
            }
            else {
                retItem = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retItem;
    }

    //事项完成时间,需要的属性为itemID,itemCompleted
    //返回ItemSet对象，但该对象除了itemID与itemCompleted属性外，其余属性均为null
    //注意移动端用itemSet.getItemID()获取其id，itemSet.getItemCompleted()获取其完成时间
    //        而后用其id来对本地数据库中的该item项设置itemCompleted参数
    public ItemSet itemCompleted(ItemSet itemSet){
        ItemSet retItem = new ItemSet();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            Timestamp timestamp = new Timestamp(itemSet.getItemCompleted().getTime());
            String sql = "update iteminfo set itemCompleted=? where itemID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, timestamp);
            stmt.setInt(2, itemSet.getItemID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retItem.setItemID(itemSet.getItemID());
                retItem.setItemCompleted(timestamp);
            }
            else {
                retItem = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retItem;
    }

    //删除事项，需要的属性为itemID
    //返回itemID，移动端可根据ID对表中数据删除
    public Integer itemDelete(ItemSet itemSet){
        int retID = 0;
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            String sql = "delete from iteminfo where itemID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemSet.getItemID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retID = itemSet.getItemID();
            }
            else {
                retID = 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retID;
    }

    //返回某用户的所有事项
    public List<ItemSet> getAllItem(Integer userID){
        List<ItemSet> list = new ArrayList<ItemSet>();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        ResultSet rs = null;
        try{
            String sql = "select * from iteminfo where userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            while(rs.next()){
                ItemSet item = new ItemSet();
                item.setItemID(rs.getInt("itemID"));
                item.setItemName(rs.getString("itemName"));
                item.setLabelID(rs.getInt("labelID"));
                item.setItemNewTime(rs.getTimestamp("itemNewTime"));
                java.util.Date dateC = new java.util.Date();
                try{
                    dateC = rs.getTimestamp("itemCompleted");
                } catch (Exception e){
                    e.printStackTrace();
                }
                item.setItemCompleted(dateC);
                java.util.Date dateDDL = new java.util.Date();
                try{
                    dateDDL = rs.getTimestamp("itemDeadline");
                } catch (Exception e){
                    e.printStackTrace();
                }
                item.setItemDeadline(dateDDL);
                item.setShowable(rs.getInt("showable"));
                item.setNoticeFlag(rs.getInt("noticeFlag"));
                item.setGroupFlag(rs.getInt("groupFlag"));
                item.setUserID(rs.getInt("userID"));
                list.add(item);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
