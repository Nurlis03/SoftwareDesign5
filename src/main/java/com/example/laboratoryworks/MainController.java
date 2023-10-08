package com.example.laboratoryworks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button ciphers;

    @FXML
    private Button salaryPayment;

    @FXML
    void onSalaryPaymentClickButton(ActionEvent event) {
        try {
            // Load the "salaries.fxml" file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("salaries.fxml"));
            Parent root = loader.load();

            // Create a new stage and set the scene to "salaries.fxml"
            Stage stage = new Stage();
            stage.setTitle("Salary payments!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCiphersClickButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cipher.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ciphers!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onQrCodeClickButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("qr-code.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ciphers!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
