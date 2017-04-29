package ddlkillerpro.db; /**
 * Created by MichelleZhang on 2017/4/12.
 */

import java.sql.*;

public class ServerDB {
    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ddldb", "root", "admin");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
