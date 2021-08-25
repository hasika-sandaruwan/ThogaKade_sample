package lk.ijse.POS.controller;

import lk.ijse.POS.db.DataBaseConnection;
import lk.ijse.POS.model.ItemsDetail;
import lk.ijse.POS.model.Order;
import lk.ijse.POS.utils.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    Connection connection = null;
    public boolean placeOrder(Order order) throws SQLException {

        try {
            connection = DataBaseConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stm1 = connection.prepareStatement("INSERT INTO Orders VALUES(?,?,?)");
            stm1.setObject(1,order.getOrderId());
            stm1.setObject(2,order.getDate());
            stm1.setObject(3,order.getCustomerId());
            boolean isOrderSaved = stm1.executeUpdate()>0;
            if (isOrderSaved) {
                boolean isSavedItemDetails = saveOrderDetails(order.getItems(), order.getOrderId());
                if (isSavedItemDetails) {
                    connection.commit();
                    return isSavedItemDetails;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
        // save order
        // order details
        // qty update
        return false;
    }

    private boolean saveOrderDetails(ArrayList<ItemsDetail> items, String orderId) throws SQLException, ClassNotFoundException {
        for (ItemsDetail temp : items
        ) {
            PreparedStatement stm2 = connection.prepareStatement("INSERT INTO OrderDetail VALUES(?,?,?,?)");
            stm2.setObject(1,orderId);
            stm2.setObject(2,temp.getCode());
            stm2.setObject(3,temp.getQty());
            stm2.setObject(4,temp.getUnitPrice());
            boolean isSaved = stm2.executeUpdate()>0;
            if (isSaved) {
                if (updateQty(temp.getCode(), temp.getQty())) {
                    //
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm3 = connection.prepareStatement("UPDATE Item SET qtyOnHand=(qtyOnHand-?) WHERE code=?");
        stm3.setObject(1, qty);
        stm3.setObject(2, id);
        return stm3.executeUpdate()>0;
    }


    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT id FROM Orders Order By id DESC LIMIT 1");
        if (set.next()) {
            int tempId = Integer.parseInt(set.getString(1).split("D")[1]);
            tempId += 1;
            if (tempId < 99) {
                return "D0" + tempId;
            } else {
                return "D" + tempId;
            }
        }
        return "D001";
    }

}
