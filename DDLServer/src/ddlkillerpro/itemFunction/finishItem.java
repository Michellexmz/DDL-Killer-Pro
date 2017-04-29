package ddlkillerpro.itemFunction;

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.daoImpl.ItemImpl;
import ddlkillerpro.json.JsonUtil1;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sjtuf on 2017/4/15.
 */
public class finishItem extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String itemId=request.getParameter("itemID");
        Integer itemID=Integer.valueOf(itemId);

        String itemCompleted=request.getParameter("itemCompleted");
        Date date=null;
        java.text.SimpleDateFormat  fm = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date = fm.parse(itemCompleted);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ItemSet item=new ItemSet();
        item.setItemID(itemID);
        item.setItemCompleted(date);
        ItemImpl ItemImpl=new ItemImpl();

        ItemSet retItem=ItemImpl.itemCompleted(item);
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
            map.put("itemName",itemname);
            map.put("itemID",itemid);
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
