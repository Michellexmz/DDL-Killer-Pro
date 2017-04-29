package ddlkillerpro.logreg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ddlkillerpro.daoImpl.UserImpl;
import ddlkillerpro.json.*;
import ddlkillerpro.bean.UserSet;
import net.sf.json.JSONObject;

/**
 * Created by MichelleZhang on 2017/4/22.
 */

public class registerServlet extends HttpServlet {

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

        String userName=request.getParameter("userName");
        String userPwd=request.getParameter("userPwd");
        String userImg=request.getParameter("userImg");

        UserImpl userDaoImpl=new UserImpl();
        UserSet user=new UserSet();
        user.setUserImg(userImg);
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        UserSet retUser=userDaoImpl.register(user);
        if(retUser!=null){
            String username=retUser.getUserName();
            Integer userid=retUser.getUserID();
            String userpwd=retUser.getUserPwd();
            String userimg=retUser.getUserImg();
            Map map = new HashMap();
            map.put("userName",username);
            map.put("userID", userid);
            map.put("userPwd",userpwd);
            map.put("userImg",userimg);
            JSONObject json = JSONObject.fromObject(map);
            out.write(json.toString());
           // String usermsg = username + "," + userid + "," +userpwd + "," + userimg;
           // String[] usermsg={username,userid.toString(userid),userpwd,userimg};
         //   out.write(usermsg);
        }
        else{
            out.write("注册失败！");
        }
        out.flush();
        out.close();
    }
}