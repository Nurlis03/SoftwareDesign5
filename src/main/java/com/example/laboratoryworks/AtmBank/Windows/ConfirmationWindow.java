package com.example.laboratoryworks.AtmBank.Windows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConfirmationWindow {
    public Optional<ButtonType> showConfirmationWindow(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердите действие");
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
