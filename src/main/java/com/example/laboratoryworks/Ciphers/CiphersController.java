package com.example.laboratoryworks.Ciphers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;


import javafx.scene.control.Button;

public class CiphersController {

    @FXML
    private TextField startField1;

    @FXML
    private TextField resultField1;

    @FXML
    private TextField startField2;

    @FXML
    private TextField keyField;

    @FXML
    private TextField resultField2;

    @FXML
    private Button buttonTwo;

    @FXML
    private Button buttonOne;

    @FXML
    private Button decodeButtonOne1;

    @FXML
    private Button decodeButtonTwo;

    @FXML
    void decodeWithKey(ActionEvent event) {
        resultField2.setText(this.applyCaesar(startField2.getText(), -(Integer.parseInt(keyField.getText()))));
    }

    @FXML
    void codeWithKey(ActionEvent event) {
        resultField2.setText(this.applyCaesar(startField2.getText(), Integer.parseInt(keyField.getText())));
    }

    @FXML
    void codeWithoutKey(ActionEvent event) {
        resultField1.setText(this.applyCaesar(startField1.getText(), 3));
    }
    @FXML
    void decodeWithoutKey(ActionEvent event) {
        resultField1.setText(this.applyCaesar(startField1.getText(), -3));
    }

    public String applyCaesar(String text, int shift)
    {
        char[] chars = text.toCharArray();
        for (int i=0; i < text.length(); i++)
        {
            char c = chars[i];
            if (c >= 32 && c <= 127)
            {
                // Change base to make life easier, and use an
                // int explicitly to avoid worrying... cast later
                int x = c - 32;
                x = (x + shift) % 96;
                if (x < 0)
                    x += 96; //java modulo can lead to negative values!
                chars[i] = (char) (x + 32);
            }
        }
        return new String(chars);
    }
}
