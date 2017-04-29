package ddlkillerpro;

import ddlkillerpro.bean.ItemSet;
import ddlkillerpro.bean.LabelSet;
import ddlkillerpro.bean.UserSet;
import ddlkillerpro.daoImpl.ItemImpl;
import ddlkillerpro.daoImpl.LabelImpl;
import ddlkillerpro.daoImpl.UserImpl;
import ddlkillerpro.db.ServerDB;
import ddlkillerpro.logreg.loginServlet;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] argv){
        LabelImpl labelImpl=new LabelImpl();
        List result=labelImpl.getAllLabel(20170503);
        System.out.println(result.toString());
    }
}
