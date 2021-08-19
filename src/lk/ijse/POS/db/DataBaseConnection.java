package lk.ijse.POS.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    /*1st Rule*/
    private static DataBaseConnection dataBaseConnection=null;
    private Connection connection;

    /*2nd Rule*/
    private DataBaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root","mysql"
        );
    }

    /*3rd Rule*/
    public static DataBaseConnection getInstance() throws SQLException, ClassNotFoundException {
       /*if (dataBaseConnection==null){
           dataBaseConnection = new DataBaseConnection();
       }
       return dataBaseConnection;*/

       return (dataBaseConnection==null?
               (dataBaseConnection=new DataBaseConnection()):(dataBaseConnection));
    }

    public Connection getConnection(){
        return connection;
    }
}
