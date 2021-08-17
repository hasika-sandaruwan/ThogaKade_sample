package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public void openSaveCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene =
                new Scene(FXMLLoader.load(getClass()
                        .getResource("../view/CustomerSaveForm.fxml")));
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void openSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void openUpdateCustomerOnAction(ActionEvent actionEvent) {
    }

    public void openDeleteCustomerOnAction(ActionEvent actionEvent) {
    }

    public void opengetAllCustomerOnAction(ActionEvent actionEvent) {
    }

    public void openPlaceOrderOnAction(ActionEvent actionEvent) {
    }
}
