package ddlkillerpro.itemFunction;

/**
 * Created by sjtuf on 2017/4/15.
 * 新建事项
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.daoImpl.ItemImpl;
import net.sf.json.JSONObject;

public class addItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String itemName=request.getParameter("itemName");

        String userId=request.getParameter("userID");
        Integer userID=Integer.valueOf(userId);

        String labelId=request.getParameter("labelID");
        Integer labelID=Integer.valueOf(labelId);

        String itemNewTime=request.getParameter("itemNewTime");
        Date date1=null;
        java.text.SimpleDateFormat  fm1 = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date1 = fm1.parse(itemNewTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String itemDeadline=request.getParameter("itemDeadline");
        Date date2=null;
        java.text.SimpleDateFormat  fm2 = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date2 = fm2.parse(itemDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String showab1e=request.getParameter("showable");
        Integer showable=Integer.valueOf(showab1e);

        String not1ceFlag=request.getParameter("noticeFlag");
        Integer noticeFlag=Integer.valueOf(not1ceFlag);

        String groupF1ag=request.getParameter("groupFlag");
        Integer groupFlag=Integer.valueOf(groupF1ag);

        ItemImpl ItemImpl=new ItemImpl();
        ItemSet item=new ItemSet();
        item.setItemName(itemName);
        item.setUserID(userID);
        item.setLabelID(labelID);

        item.setItemNewTime(new Timestamp(date1.getTime()));
        item.setItemDeadline(date2);
        item.setShowable(showable);
        item.setGroupFlag(groupFlag);
        item.setNoticeFlag(noticeFlag);

        ItemSet retItem=ItemImpl.addItem(item);
        if(retItem!=null){
            Integer itemid=retItem.getItemID();
            String itemname=retItem.getItemName();
            Integer labelid=retItem.getLabelID();
            Integer userid=retItem.getUserID();
            Timestamp itemnewtime=retItem.getItemNewTime();
            Date itemcompleted=retItem.getItemCompleted();
            Date itemdeadline=retItem.getItemDeadline();
            Integer showable1=retItem.isShowable();
            Integer noticeflag=retItem.isNoticeFlag();
            Integer groupflag=retItem.isGroupFlag();

            Map map = new HashMap();
            map.put("itemID",itemid);
            map.put("itemName",itemname);
            map.put("labelID",labelid);
            map.put("userID",userid);
            map.put("itemNewTime",itemnewtime);
            map.put("itemCompleted",itemcompleted);
            map.put("itemDeadline",itemdeadline);
            map.put("showable",showable1);
            map.put("noticeFlag",noticeflag);
            map.put("groupFlag",groupflag);
            JSONObject json = JSONObject.fromObject(map);
            out.write(json.toString());
        }
        else{
            out.write("failed");
        }
        out.flush();
        out.close();
    }
}
