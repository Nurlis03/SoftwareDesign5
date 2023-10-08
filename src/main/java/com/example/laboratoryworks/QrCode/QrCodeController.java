package com.example.laboratoryworks.QrCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import java.io.IOException;


public class QrCodeController {

    @FXML
    private ImageView qrCodeImage;

    @FXML
    private Label qrCodeLabel;

    @FXML
    private Button invert;

    @FXML
    private Button invert1;

    @FXML
    private TextField initialField1;

    @FXML
    private TextField initialField2;

    @FXML
    private Label welcomeText;

    @FXML
    void showQrCode(ActionEvent event) throws IOException {
        System.out.println("src/main/java/com/example/laboratoryworks/QrCode/" + initialField2.getText() + ".jpg");
        File file = new File("src/main/java/com/example/laboratoryworks/QrCode/" + initialField2.getText() + ".jpg");
        Image image = new Image(file.toURI().toString());
        qrCodeImage.setImage(image);
    }

    @FXML
    public void createQrCode() throws OutputException, BarcodeException, IOException {
        Barcode barcode = BarcodeFactory.createCode128B(initialField1.getText());
        File imgFile = new File("src/main/java/com/example/laboratoryworks/QrCode/" + initialField1.getText() + ".jpg");
        BarcodeImageHandler.saveJPEG(barcode, imgFile);
    }
}
