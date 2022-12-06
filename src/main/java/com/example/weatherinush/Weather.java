package com.example.weatherinush;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;




public class Weather {


    @FXML
    private TextField DayField;

    @FXML
    private Button SaveButton;



    @FXML
    private TextField FieldTemperature;

    @FXML
    private TextField FieldFelt;

    @FXML
    private TextField FieldPricipitation;



    @FXML
    void initialize()  {
        DataBaseHandler dbHandler = new DataBaseHandler();

        SaveButton.setOnAction(actionEvent -> {
            dbHandler.addInfoInData(DayField.getText(), FieldPricipitation.getText(),
                    FieldTemperature.getText(), FieldFelt.getText());
        });


    }
}




