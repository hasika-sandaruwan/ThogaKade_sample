package lk.ijse.POS.model;

import java.util.Objects;

public class Item {
    private String code;
    private String description;
    private double untPrice;
    private int qtyOnHand;

    public Item() {
    }

    public Item(String code, String description, double untPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.untPrice = untPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUntPrice() {
        return untPrice;
    }

    public void setUntPrice(double untPrice) {
        this.untPrice = untPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", untPrice=" + untPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(code, item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
