package lk.ijse.POS.controller;

import lk.ijse.POS.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IteController {
    public ArrayList<String> getAllItemCodes() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item");
        while (rst.next()) {
            list.add(rst.getString(1));
        }
        return list;
    }
}
