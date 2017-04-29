package ddlkillerpro.labelFunction;

import ddlkillerpro.bean.LabelSet;
import ddlkillerpro.daoImpl.LabelImpl;
import ddlkillerpro.json.JsonUtil2;

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
public class deleteLabel extends HttpServlet{

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

        String labelId=request.getParameter("labelID");
        Integer labelID=Integer.valueOf(labelId);
        LabelSet label=new LabelSet();
        label.setLabelID(labelID);

        LabelImpl ItemImpl = new LabelImpl();
        Integer retId = ItemImpl.labelDelete(label);
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
