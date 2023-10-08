module com.example.laboratoryworks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires barbecue;

    exports com.example.laboratoryworks;
    exports com.example.laboratoryworks.SalaryPayments;
    exports com.example.laboratoryworks.Ciphers;
    exports com.example.laboratoryworks.QrCode;
    exports com.example.laboratoryworks.AtmBank;
    opens com.example.laboratoryworks to javafx.fxml;
    opens com.example.laboratoryworks.SalaryPayments to javafx.fxml;
    opens com.example.laboratoryworks.Ciphers to javafx.fxml;
    opens com.example.laboratoryworks.QrCode to javafx.fxml;
    opens com.example.laboratoryworks.AtmBank to javafx.fxml;
}