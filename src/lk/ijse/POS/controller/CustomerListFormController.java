package lk.ijse.POS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.POS.model.Customer;
import lk.ijse.POS.view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerListFormController {
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> list= new CustomerController().getAllCustomers();
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        for (Customer temp:list
             ) {
            obList.add(
                    new CustomerTM(temp.getId(),temp.getName(),temp.getAddress(),temp.getSalary())
            );
        }
        tblCustomer.setItems(obList);
    }

}
