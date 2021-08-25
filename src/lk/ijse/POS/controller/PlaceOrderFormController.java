package lk.ijse.POS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;

public class PlaceOrderFormController {
    public ComboBox<String> cmbCustomerId;
    public ComboBox<String> cmbItemCode;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadCustomerIds();
        loadItemCodes();
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList= FXCollections.observableArrayList
                (new IteController().getAllItemCodes());
        cmbItemCode.setItems(obList);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList= FXCollections.observableArrayList
                (new CustomerController().getAllCustomerIds());
        cmbCustomerId.setItems(obList);
    }

}
