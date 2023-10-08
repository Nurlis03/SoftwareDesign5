package com.example.laboratoryworks.birds;

import com.example.laboratoryworks.MainApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BirdsController {

    @FXML
    private RadioButton birdsRadio;

    @FXML
    private RadioButton penguinRadio;

    @FXML
    private Button activateButton;

    @FXML
    private ImageView imageView;

    public MediaPlayer mediaPlayer;

    @FXML
    void onActivateButtonClick(ActionEvent event) {
        String imageName;
        String musicName;

        if (birdsRadio.isSelected()) {
            imageName = "1.gif";
            musicName = "1.mp3";
        } else {
            imageName = "2.gif";
            musicName = "2.mp3";
        }

        try {
            mediaPlayer.stop();
        } catch (Exception e) {

        }

        String imageUri = new File("src/main/java/com/example/laboratoryworks/birds/" + imageName).toURI().toString();
        Image image = new Image(imageUri);
        imageView.setImage(image);

        String musicUri = new File("src/main/java/com/example/laboratoryworks/birds/" + musicName).toURI().toString();
        Media sound = new Media(musicUri);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1000);
        mediaPlayer.play();
    }

    @FXML
    void onBirdsRadioChange() {
        penguinRadio.setSelected(false);
    }
    @FXML
    void onPenguinRadioChange() {
        birdsRadio.setSelected(false);
    }

    // Create a public method to stop the media player
    public void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
