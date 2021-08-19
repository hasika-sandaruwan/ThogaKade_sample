package lk.ijse.POS.controller;

import lk.ijse.POS.db.DataBaseConnection;
import lk.ijse.POS.model.Customer;
import lk.ijse.POS.utils.CrudUtil;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class CustomerController {

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",c.getId(),c.getName(),c.getAddress(),c.getSalary());
    }

    public Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE id=?",id);
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
        return CrudUtil.execute("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?", c.getName(),c.getAddress(),c.getSalary(), c.getId());
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        ArrayList<Customer> customerArray= new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        while (rst.next()){
            customerArray.add(
                    new Customer(
                            rst.getString(1),rst.getString(2),
                            rst.getString(3),rst.getDouble("salary")
                    )
            );
        }
        return customerArray;
    }
}
