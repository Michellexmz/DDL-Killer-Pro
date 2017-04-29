package ddlkillerpro.daoImpl;

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.bean.LabelSet;
import ddlkillerpro.db.ServerDB;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichelleZhang on 2017/4/12.
 */
public class LabelImpl {
    //添加分类，返回值为LabelSet对象
    public LabelSet addLabel(LabelSet labelSet){
        LabelSet retLabel = new LabelSet();
        ServerDB serverDB = new ServerDB();
        int labelID = 0;
        Connection conn = serverDB.getConnection();
        ResultSet rs = null;
        try{
            String sql = "insert into labelinfo (labelName, labelLogo, labelColor) values (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, labelSet.getLabelName());
            stmt.setInt(2, labelSet.getLabelLogo());
            stmt.setString(3, labelSet.getLabelColor());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                labelID = rs.getInt(1);
                labelSet.setLabelID(labelID);
                retLabel = labelSet;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        serverDB.closeConnection(conn);
        return retLabel;
    }

    //修改分类属性,返回值为LabelSet对象
    public LabelSet setLabel(LabelSet labelSet){
        LabelSet retLabel = new LabelSet();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            String sql = "Update labelinfo set labelName = ?, labelLogo = ?, labelColor = ? where labelID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, labelSet.getLabelName());
            stmt.setInt(2, labelSet.getLabelLogo());
            stmt.setString(3, labelSet.getLabelColor());
            stmt.setInt(4, labelSet.getLabelID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retLabel = labelSet;
            }
            else {
                retLabel = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retLabel;
    }

    //删除分类,返回labelID，移动端可根据ID对表中数据删除
    public Integer labelDelete(LabelSet labelSet){
        int retID = 0;
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        try{
            String sqlItem = "delete from iteminfo where labelID = ?";
            PreparedStatement ps = conn.prepareStatement(sqlItem);
            ps.setInt(1, labelSet.getLabelID());
            ps.executeUpdate();

            String sql = "delete from labelinfo where labelID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, labelSet.getLabelID());
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0){
                retID = labelSet.getLabelID();
            }
            else {
                retID = 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return retID;
    }

    //返回所有分类信息
    public List<LabelSet> getAllLabel(Integer userID){
        List<LabelSet> list = new ArrayList<LabelSet>();
        ServerDB serverDB = new ServerDB();
        Connection conn = serverDB.getConnection();
        ResultSet rs = null;
        try{
            String sql = "select DISTINCT (a.labelID), a.labelColor, a.labelLogo, a.labelName from labelinfo a, iteminfo b where b.userID = ? and a.labelID = b.labelID";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            while(rs.next()){
                LabelSet label = new LabelSet();
                label.setLabelID(rs.getInt("labelID"));
                label.setLabelColor(rs.getString("labelColor"));
                label.setLabelLogo(rs.getInt("labelLogo"));
                label.setLabelName(rs.getString("labelName"));
                list.add(label);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
