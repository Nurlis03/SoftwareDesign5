package com.example.laboratoryworks.AtmBank;

import com.example.laboratoryworks.AtmBank.DatabaseConnection;
import com.example.laboratoryworks.AtmBank.Windows.AlertWindow;
import com.example.laboratoryworks.AtmBank.Windows.ErrorWindow;
import com.example.laboratoryworks.AtmBank.Windows.ConfirmationWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Date;

public class AtmBankController {

    @FXML
    private Button zeroButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private TextField codeField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button depositeButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button balanceButton;


    public boolean isLoggedIn = false;
    public String accountCode = "";

    public void onZeroButtonClick() {
        this.updateCodeField("0");
    }
    public void onOneButtonClick() {
        this.updateCodeField("1");
    }
    public void onTwoButtonClick() {
        this.updateCodeField("2");
    }
    public void onThreeButtonClick() {
        this.updateCodeField("3");
    }
    public void onFourButtonClick() {
        this.updateCodeField("4");
    }
    public void onFiveButtonClick() {
        this.updateCodeField("5");
    }
    public void onSixButtonClick() {
        this.updateCodeField("6");
    }
    public void onSevenButtonClick() {
        this.updateCodeField("7");
    }
    public void onEightButtonClick() {
        this.updateCodeField("8");
    }
    public void onNineButtonClick() {
        this.updateCodeField("9");
    }
    public void updateCodeField (String value) {
        codeField.setText(codeField.getText() + value);
    }
    public void onCancelButtonClick () {
        Optional<ButtonType> result = new ConfirmationWindow().showConfirmationWindow("Exit the app", "Are you sure you want to quit the app?");
        if (result.get() == ButtonType.OK){
            System.exit(1);
        }
    }

    public void onClearButtonClick () {
        codeField.setText("");
    }

    public void onDepositeClickButton () throws SQLException {
        if (!this.isLoggedIn) {
            new ErrorWindow().showWindow("Error", "You are not logged in to deposite money");
            return;
        }
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Deposite");
        dialog.setHeaderText("Deposite amount to be entered");
        dialog.setContentText("Please enter amount:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> System.out.println("Your name: " + s));

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();
            Statement statement = dbLink.createStatement();
            statement.executeUpdate("UPDATE accounts SET amount = amount + '" + Double.parseDouble(result.get()) + "', createdAt = '" + new Date().toString() + "' WHERE code = '" + this.accountCode + "'");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onBalanceButtonClick () throws SQLException {
        if (!this.isLoggedIn) {
            new ErrorWindow().showWindow("Error", "You are not logged in to view balance");
            return;
        }
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();

            Statement statement = dbLink.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts WHERE code = '" + this.accountCode + "'");
            resultSet.next();
            new AlertWindow().showAlertWindow("Balance", "Your balance = " + resultSet.getString("amount") + ".\n Last updated at " + resultSet.getString("createdAt"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onWithdrawButtonClick() {
        if (!this.isLoggedIn) {
            new ErrorWindow().showWindow("Error", "You are not logged in to withdraw money");
            return;
        }

        // Fetch the current balance from the database
        double currentBalance = getAccountBalance(this.accountCode);

        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Withdraw");
        dialog.setHeaderText("Withdraw amount to be entered");
        dialog.setContentText("Please enter amount:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                double withdrawAmount = Double.parseDouble(result.get());
                if (withdrawAmount < 0) {
                    new ErrorWindow().showWindow("Error", "Please enter a positive amount");
                } else if (withdrawAmount > currentBalance) {
                    new ErrorWindow().showWindow("Error", "Insufficient balance");
                } else {
                    // Perform the withdrawal
                    updateBalance(this.accountCode, currentBalance, withdrawAmount);
                }
            } catch (NumberFormatException e) {
                new ErrorWindow().showWindow("Error", "Invalid input. Please enter a valid number.");
            }
        }
    }

    private double getAccountBalance(String accountCode) {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();
            Statement statement = dbLink.createStatement();

            // SQL query to fetch the current balance from the 'accounts' table
            String sqlQuery = "SELECT amount FROM accounts WHERE code = '" + accountCode + "'";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                double balance = resultSet.getDouble("amount");
                resultSet.close();
                return balance;
            } else {
                resultSet.close();
                return 0.0; // Return a default balance (you can handle this differently based on your requirements)
            }
        } catch (Exception e) {
            new AlertWindow().showErrorWindow("Database Error", e.getMessage());
            return 0.0; // Return a default balance in case of an error
        }
    }

    private void updateBalance(String accountCode, double currentBalance, double withdrawAmount) {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();
            Statement statement = dbLink.createStatement();
            statement.executeUpdate("UPDATE accounts SET amount = amount - '" + withdrawAmount + "', createdAt = '" + new Date().toString() + "' WHERE code = '" + accountCode + "'");
            double newBalance = currentBalance - withdrawAmount;
            System.out.println("Withdrawn amount: " + withdrawAmount);
            System.out.println("New balance: " + newBalance);
        } catch (Exception e) {
            new AlertWindow().showErrorWindow("Withdraw error", e.getMessage());
        }
    }


    public void onEnterButtonClick () throws SQLException {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();

            Statement statement = dbLink.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts WHERE code = '" + codeField.getText() + "'");
            if (resultSet.next()) {
                new AlertWindow().showAlertWindow("Login", "Welcome to system");
                this.isLoggedIn = true;
                this.accountCode = codeField.getText();
            } else {
                new AlertWindow().showErrorWindow("Login error", "No such account with such pin code");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRegisterButtonClick () {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Register");
        dialog.setHeaderText("Register your account to use it un future");
        dialog.setContentText("Please enter account secret:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection dbLink = dbConnection.getDatabaseLink();
            Statement statement = dbLink.createStatement();
            statement.executeUpdate("INSERT INTO accounts (`code`, `amount`, `createdAt`) VALUES ('"+result.get()+"', 0, '" + new Date().toString()+ "')");
        } catch (Exception e) {
            new AlertWindow().showErrorWindow("Register error", e.getMessage());
        }
    }
}
