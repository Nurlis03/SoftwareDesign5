package com.example.laboratoryworks;

import com.example.laboratoryworks.birds.BirdsController;
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
            stage.setTitle("Выдача зарплаты!");
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
            stage.setTitle("Шифр Цезаря!");
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
            stage.setTitle("QR код!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAtmBankClickButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("atm-bank.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("АТМ банк!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BirdsController birdsController; // Reference to BirdsController

    @FXML
    void onPenguinsBirdsClickButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("birds.fxml"));
            Parent root = loader.load();

            birdsController = loader.getController(); // Get the reference to BirdsController

            Stage stage = new Stage();
            stage.setTitle("Птицы!");
            stage.setScene(new Scene(root));
            stage.show();

            // Set the close request handler for the child window
            stage.setOnCloseRequest(event -> {
                if (birdsController != null) {
                    birdsController.stopMediaPlayer(); // Call the stop method in BirdsController
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
