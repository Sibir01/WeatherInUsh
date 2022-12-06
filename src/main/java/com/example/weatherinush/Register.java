package com.example.weatherinush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.HashMap;



public class Register {


    @FXML
    private TextField NameField;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField LastNameField;

    @FXML
    private Button EnterButton;

    @FXML
    private Button BackButton;

    @FXML
    void initialize() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        EnterButton.setOnAction(actionEvent -> {
            dbHandler.signUpUser(NameField.getText(),LastNameField.getText(),
                    LoginField.getText(),PasswordField.getText());
        });


        //переход назад по кнопке )0))
        BackButton.setOnAction(actionEvent -> {
            OpenNewScene("hello-view.fxml");

        });

    }
    private void OpenNewScene(String window) {

       BackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();



    }





}
