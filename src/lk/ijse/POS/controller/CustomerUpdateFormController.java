package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerUpdateFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtId.getText(), txtName.getText(), txtAddress.getText()
                , Double.parseDouble(txtSalary.getText())
        );
        try {
            if (new CustomerController().updateCustomer(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void SearchCustomerOnAction(ActionEvent actionEvent) {
        try {

            Customer customer = new CustomerController().searchCustomer(txtId.getText());
            if (customer != null) {
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtSalary.setText(String.valueOf(customer.getSalary()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
