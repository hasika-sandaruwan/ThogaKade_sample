package lk.ijse.POS.controller;

import lk.ijse.POS.model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerController {

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade", "root", "mysql");
        String query = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getSalary());
        return stm.executeUpdate() > 0;

    }

    public Customer searchCustomer(String id) {
        return null;
    }

    public boolean updateCustomer(Customer c) {
        return false;
    }

    public boolean deleteCustomer(String id) {
        return false;
    }

    public ArrayList<Customer> getAllCustomers() {
        return null;
    }
}
