package ddlkillerpro.itemFunction;

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.daoImpl.ItemImpl;
import ddlkillerpro.json.WriteJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by sjtuf on 2017/4/15.
 */

public class showAllItems extends HttpServlet {

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
        String userId = request.getParameter("userID");
        Integer userID = Integer.valueOf(userId);
        ItemImpl itemImpl = new ItemImpl();
        List<ItemSet> allItem = itemImpl.getAllItem(userID);
        WriteJson writeJson = new WriteJson();
        String allItemJ = writeJson.getJsonData(allItem);
        out.write(allItemJ);
    }
}