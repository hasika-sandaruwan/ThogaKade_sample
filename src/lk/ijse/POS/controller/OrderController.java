package lk.ijse.POS.controller;

import lk.ijse.POS.model.ItemsDetail;
import lk.ijse.POS.model.Order;
import lk.ijse.POS.utils.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public boolean placeOrder(Order order) {

        try {

            boolean isOrderSaved = CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?)",
                    order.getOrderId(),order.getDate(), order.getCustomerId()
            );
            if (isOrderSaved){
               boolean isSavedItemDetails = saveOrderDetails(order.getItems(), order.getOrderId());
               if (isSavedItemDetails){
                   return isSavedItemDetails;
               }else{
                   return false;
               }
            }else{
                return false;
            }


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

    private boolean saveOrderDetails(ArrayList<ItemsDetail> items, String orderId) throws SQLException, ClassNotFoundException {
        for (ItemsDetail temp :items
             ) {
            boolean isSaved = CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?)",
                    orderId,temp.getCode(),temp.getQty(),temp.getUnitPrice());
            if (isSaved){
                //
            }else{
                return false;
            }
        }
        return true;
    }
}
