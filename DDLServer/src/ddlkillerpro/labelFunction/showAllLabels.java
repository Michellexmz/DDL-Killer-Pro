package ddlkillerpro.labelFunction;

import ddlkillerpro.bean.LabelSet;
import ddlkillerpro.daoImpl.LabelImpl;
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
public class showAllLabels extends HttpServlet{

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
        LabelImpl labelImpl = new LabelImpl();
        List<LabelSet> allLabels = labelImpl.getAllLabel(userID);
        WriteJson writeJson = new WriteJson();
        String allLabelJ = writeJson.getJsonData(allLabels);
        out.write(allLabelJ);
    }
}
