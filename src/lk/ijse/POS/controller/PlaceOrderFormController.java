package lk.ijse.POS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.POS.model.Customer;
import lk.ijse.POS.model.Item;
import lk.ijse.POS.view.tm.CartTM;

import java.sql.SQLException;

public class PlaceOrderFormController {
    public ComboBox<String> cmbCustomerId;
    public ComboBox<String> cmbItemCode;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtQtyForOrder;

    public TableView<CartTM> tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TableColumn colOption;

    public void initialize() throws SQLException, ClassNotFoundException {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));


        loadCustomerIds();
        loadItemCodes();

        //-----------------------------------------------------

        cmbCustomerId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        cmbItemCode.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    try {
                        setItemData(newValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private void setItemData(String id) throws SQLException, ClassNotFoundException {
        Item temp = new ItemController().searchItem(id);
        txtDescription.setText(temp.getDescription());
        txtQtyOnHand.setText(String.valueOf(temp.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(temp.getUntPrice()));
    }

    private void setCustomerData(String id) throws SQLException, ClassNotFoundException {
        Customer temp = new CustomerController().searchCustomer(id);
        txtName.setText(temp.getName());
        txtAddress.setText(temp.getAddress());
        txtSalary.setText(String.valueOf(temp.getSalary()));
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList
                (new ItemController().getAllItemCodes());
        cmbItemCode.setItems(obList);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList
                (new CustomerController().getAllCustomerIds());
        cmbCustomerId.setItems(obList);
    }


    ObservableList<CartTM> obList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQtyForOrder.getText());
        double total = unitPrice * qtyForCustomer;

        Button btn = new Button("Delete");

        CartTM tm = new CartTM(
                cmbItemCode.getValue(),
                txtDescription.getText(),
                unitPrice,
                qtyForCustomer,
                total,
                btn
        );

        btn.setOnAction((e)->{
            obList.remove(tm);
            tblCart.refresh();
        });

        int rowNumber = isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
            tblCart.setItems(obList);
        }else{
            obList.get(rowNumber).setQty(obList.get(rowNumber).getQty()+qtyForCustomer);
            obList.get(rowNumber).setTotal(obList.get(rowNumber).getTotal()+total);
            tblCart.refresh();
        }
    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getCode().equals(tm.getCode())) {
                return i;
            }
        }
        return -1;
    }
}
