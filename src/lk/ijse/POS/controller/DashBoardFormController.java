package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public void openSaveCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerSaveForm");

    }

    public void openSearchCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerSearchForm");
    }

    public void openUpdateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerUpdateForm");
    }

    public void openDeleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerDeleteForm");
    }

    public void opengetAllCustomerOnAction(ActionEvent actionEvent) {
    }

    public void openPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    void setUi(String location) throws IOException {
        Scene scene =
                new Scene(FXMLLoader.load(getClass()
                        .getResource("../view/"+location+".fxml")));
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
