package ddlkillerpro.logreg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ddlkillerpro.bean.UserSet;
import ddlkillerpro.daoImpl.UserImpl;
import net.sf.json.JSONObject;

/**
 * Created by MichelleZhang on 2017/4/22.
 */


public class loginServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String userId=request.getParameter("userID");
        Integer userID=Integer.valueOf(userId);
        String userPwd=request.getParameter("userPwd");
        UserImpl userImpl=new UserImpl();
        UserSet retUser=userImpl.login(userID,userPwd);
        if(retUser != null){
            String username=retUser.getUserName();
            Integer userid=retUser.getUserID();
            String userpwd=retUser.getUserPwd();
            String userimg=retUser.getUserImg();
            Map map = new HashMap();
            map.put("userID", userid);
            map.put("userName",username);
            map.put("userPwd",userpwd);
            map.put("userImg",userimg);
            JSONObject json = JSONObject.fromObject(map);
            out.write(json.toString());
        }
        else {
            out.write("Login Failed");
        }
        out.flush();
        out.close();
    }

}