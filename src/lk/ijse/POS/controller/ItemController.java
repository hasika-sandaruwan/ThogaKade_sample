package lk.ijse.POS.controller;

import lk.ijse.POS.model.Customer;
import lk.ijse.POS.model.Item;
import lk.ijse.POS.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    public ArrayList<String> getAllItemCodes() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item");
        while (rst.next()) {
            list.add(rst.getString(1));
        }
        return list;
    }

    public Item searchItem(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?",id);
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

}
