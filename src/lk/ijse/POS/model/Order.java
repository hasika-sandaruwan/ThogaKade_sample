package lk.ijse.POS.model;


import java.util.ArrayList;

public class Order {
    private String orderId;
    private String date;
    private String customerId;
    private ArrayList<ItemsDetail> items;

    public Order() {
    }

    public Order(String orderId, String date, String customerId, ArrayList<ItemsDetail> items) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<ItemsDetail> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsDetail> items) {
        this.items = items;
    }
}
