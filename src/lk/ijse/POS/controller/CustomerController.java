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
        stm.setObject(1, c.getId());
        stm.setObject(2, c.getName());
        stm.setObject(3, c.getAddress());
        stm.setObject(4, c.getSalary());
        return stm.executeUpdate() > 0;

    }

    public Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade", "root", "mysql");
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Customer WHERE id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble("salary")
            );
        }
        return null;
    }

    public boolean updateCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/ThogaKade",
                        "root","mysql");
        PreparedStatement stm =connection.
                prepareStatement("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?");
        stm.setObject(1,c.getName());
        stm.setObject(2,c.getAddress());
        stm.setObject(3,c.getSalary());
        stm.setObject(4,c.getId());
        return stm.executeUpdate()>0;
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/ThogaKade",
                        "root","mysql");
        PreparedStatement stm = connection.prepareStatement(
                "DELETE FROM Customer WHERE id=?"
        );
        stm.setObject(1,id);
        return stm.executeUpdate()>0;
    }

    public ArrayList<Customer> getAllCustomers() {
        return null;
    }
}
