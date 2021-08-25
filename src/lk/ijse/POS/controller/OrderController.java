package lk.ijse.POS.controller;

import lk.ijse.POS.model.Order;
import lk.ijse.POS.utils.CrudUtil;

import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order) {

        try {

            boolean isOrderSaved = CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?)",
                    order.getOrderId(),order.getDate(), order.getCustomerId()
            );
            return isOrderSaved;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // save order
        // order details
        // qty update
        return false;
    }
}
