package ddlkillerpro.logreg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import ddlkillerpro.json.*;
import ddlkillerpro.bean.UserSet;
import ddlkillerpro.daoImpl.UserImpl;

/**
 * Created by MichelleZhang on 2017/4/22.
 */

public class setUser extends HttpServlet {

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
        String jsondata = request.getParameter("jsonstring");
        JsonUtil jsonUtil = new JsonUtil();
        List<UserSet> list = jsonUtil.StringFromJson(jsondata);
        UserSet user = list.get(0);
        UserImpl userDaoImpl = new UserImpl();
        UserSet retUser = userDaoImpl.setUser(user);
        if (retUser!=null) {
            out.write("修改成功！");
        } else {
            out.write("修改失败");
        }
    }
}
