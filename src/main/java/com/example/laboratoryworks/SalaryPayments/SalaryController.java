package com.example.laboratoryworks.SalaryPayments;

import com.example.laboratoryworks.SalaryPayments.Windows.ErrorWindow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {
    @FXML
    private TableView<PaymentEntity> paymentsTable;

    @FXML
    private TableColumn<PaymentEntity, Integer> id;

    @FXML
    private TableColumn<PaymentEntity, String> name;

    @FXML
    private TableColumn<PaymentEntity, String> address;

    @FXML
    private TableColumn<PaymentEntity, String> phone;

    @FXML
    private TableColumn<PaymentEntity, String> positionName;

    @FXML
    private TableColumn<PaymentEntity, Double> profit_tax;

    @FXML
    private TableColumn<PaymentEntity, Double> prof_tax;

    @FXML
    private TableColumn<PaymentEntity, Double> retirement_tax;

    @FXML
    private TableColumn<PaymentEntity, LocalDate> date;

    @FXML
    private TableColumn<PaymentEntity, Double> total;

    @FXML
    private TextField profitTaxField;

    @FXML
    private TextField profTaxField;

    @FXML
    private TextField retirementTaxField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button countButton;

    @FXML
    private TextField totalPaiedField;

    @FXML
    private TextField calendarDaysField;

    @FXML
    private TextField workDaysField;

    @FXML
    private TextField benefitMoneyField;

    public void setInitialValues() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ObservableList<PaymentEntity> payments  = dbConnection.getPayments(connection);
        String totalPayments = dbConnection.countAllPayments(connection);
        if (totalPayments != null) {
            totalPaiedField.setText(totalPayments);
        } else {
            // Handle the case where the value is null, e.g., set a default value or show an error message.
        }
        paymentsTable.setItems(payments);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        positionName.setCellValueFactory(new PropertyValueFactory<>("positionName"));
        profit_tax.setCellValueFactory(new PropertyValueFactory<>("profit_tax"));
        prof_tax.setCellValueFactory(new PropertyValueFactory<>("prof_tax"));
        retirement_tax.setCellValueFactory(new PropertyValueFactory<>("retirement_tax"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            setInitialValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void countSalaries(ActionEvent event) throws SQLException {
        if (Integer.parseInt(calendarDaysField.getText()) < Integer.parseInt(workDaysField.getText())) {
            new ErrorWindow().showWindow("Расчет зарплат", "Количество рабочих дней больше количества календарных дней. Измените ввод");
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ResultSet employeesResultSet = dbConnection.getEmployees(connection);
        dbConnection.createPayments(
                connection,
                employeesResultSet,
                Double.parseDouble(profitTaxField.getText()),
                Double.parseDouble(profTaxField.getText()),
                Double.parseDouble(retirementTaxField.getText()),
                Integer.parseInt(calendarDaysField.getText()),
                Integer.parseInt(workDaysField.getText()),
                Double.parseDouble(benefitMoneyField.getText()),
                dateField.getValue());

        ObservableList<PaymentEntity> payments  = dbConnection.getPayments(connection);
        paymentsTable.setItems(payments);
        totalPaiedField.setText(dbConnection.countAllPayments(connection));
    }
}

