package ddlkillerpro.labelFunction;

import ddlkillerpro.bean.LabelSet;
import ddlkillerpro.daoImpl.LabelImpl;
import ddlkillerpro.json.JsonUtil2;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sjtuf on 2017/4/15.
 */
public class setLabel extends HttpServlet {

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

        String labelName=request.getParameter("labelName");
        String labelLogo=request.getParameter("labelLogo");
        String labelColor=request.getParameter("labelColor");
        String labelId=request.getParameter("labelID");
        Integer labelID=Integer.valueOf(labelId);

        LabelSet label=new LabelSet();
        label.setLabelName(labelName);
        label.setLabelLogo(Integer.valueOf(labelLogo));
        label.setLabelColor(labelColor);
        label.setLabelID(labelID);

        LabelImpl ItemImpl = new LabelImpl();
        LabelSet retLabel = ItemImpl.setLabel(label);
        if(retLabel!=null){
            String labelname=retLabel.getLabelName();
            Integer labellogo=retLabel.getLabelLogo();
            String labelcolor=retLabel.getLabelColor();
            Integer labelid=retLabel.getLabelID();

            Map map = new HashMap();
            map.put("labelID",labelid);
            map.put("labelName",labelname);
            map.put("labelLogo",labellogo);
            map.put("labelColor",labelcolor);

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
