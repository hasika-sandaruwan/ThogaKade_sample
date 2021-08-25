package lk.ijse.POS.controller;

import lk.ijse.POS.model.ItemsDetail;
import lk.ijse.POS.model.Order;
import lk.ijse.POS.utils.CrudUtil;

import java.sql.ResultSet;
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
                if (updateQty(temp.getCode(), temp.getQty())){
                    //
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("UPDATE Item SET qtyOnHand=(qtyOnHand-?) WHERE code=?", qty,id);
    }


    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT id FROM Orders Order By id DESC LIMIT 1");
        if (set.next()){
            int tempId = Integer.parseInt(set.getString(1).split("D")[1]);
            tempId+=1;
            if (tempId<99){
                return "D0"+tempId;
            }else {
                return "D"+tempId;
            }
        }
        return "D001";
    }

}
