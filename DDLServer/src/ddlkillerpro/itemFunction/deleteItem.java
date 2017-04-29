package ddlkillerpro.itemFunction;

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.daoImpl.ItemImpl;
import ddlkillerpro.json.JsonUtil;

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
public class deleteItem extends HttpServlet {

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

        String itemId=request.getParameter("itemID");
        Integer itemID=Integer.valueOf(itemId);
        ItemSet item=new ItemSet();
        item.setItemID(itemID);

        ItemImpl ItemImpl = new ItemImpl();
        Integer retId = ItemImpl.itemDelete(item);
        String retID=retId.toString();
        if (retId!=0) {
            out.write(retID);
        } else {
            out.write("failed");
        }
        out.flush();
        out.close();
    }
}
