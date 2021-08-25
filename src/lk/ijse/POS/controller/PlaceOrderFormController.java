package lk.ijse.POS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class PlaceOrderFormController {
    public ComboBox<String> cmbCustomerId;
    public ComboBox<String> cmbItemCode;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void initialize() throws SQLException, ClassNotFoundException {
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
    }

    private void setCustomerData(String id) throws SQLException, ClassNotFoundException {
        Customer temp = new CustomerController().searchCustomer(id);
        txtName.setText(temp.getName());
        txtAddress.setText(temp.getAddress());
        txtSalary.setText(String.valueOf(temp.getSalary()));
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
