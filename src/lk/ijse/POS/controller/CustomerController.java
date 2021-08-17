package lk.ijse.POS.controller;

import lk.ijse.POS.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerController {

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        // load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // create Connection (import java.sql.Connection)
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade", "root", "mysql");
        // create SQL
        String query = "INSERT INTO Customer VALUES ('" + c.getId() +
                "','" + c.getName() + "','" + c.getAddress() + "','" + c.getSalary() + "')";
        // create Statement
        Statement stm = con.createStatement();

        // statement execute (INSERT,UPDATE,DELETE[executeUpdate]) (SELECT[executeQuery])
        return stm.executeUpdate(query)>0;
        /*return isSaved>0;*/
        /*if (isSaved>0){
            return true;
        }
        return false;*/
    }

    public Customer searchCustomer(String id) {
    }

    public boolean updateCustomer(Customer c) {
    }

    public boolean deleteCustomer(String id) {
    }

    public ArrayList<Customer> getAllCustomers() {
    }
}
