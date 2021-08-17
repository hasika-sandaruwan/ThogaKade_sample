package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerSearchFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void searchCustomerOnAction(ActionEvent actionEvent) {

        try {

            Customer c1= new CustomerController().searchCustomer(txtId.getText());

            if (c1!=null){
                txtName.setText(c1.getName());
                txtAddress.setText(c1.getAddress());
                txtSalary.setText(String.valueOf(c1.getSalary()));
            }else{
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
