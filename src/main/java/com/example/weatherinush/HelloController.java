package com.example.weatherinush;

import Animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.stage.Stage;
import org.json.JSONObject;

public class HelloController {

    @FXML
    private Button RegisterButton;

    @FXML
    private Button EnterButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField LoginField;

    @FXML
    void initialize() { //проверка ввели ли логин и пароль

        EnterButton.setOnAction(actionEvent -> {
            String LoginText = LoginField.getText().trim();
            String loginPassword = PasswordField.getText().trim();

            if(!LoginText.equals("") && !loginPassword.equals(""))
                loginUser(LoginText, loginPassword);
                else
                    System.out.println("Login or password is empty");

        });

        RegisterButton.setOnAction(actionEvent -> {
            OpenNewScene("Register.fxml");

        });
    }
//Пишем код для проверки существует ли пользователь в базе данных. И потом для входа
    private void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

       try {
           while(result.next()) {
               counter++;
           }
           } catch (SQLException e) {
               e.printStackTrace();
           }


        if(counter >= 1) {
            OpenNewScene("Weather.fxml");
            
        } else { //Если пароль введен не правильно то происходит анимация шейка
                Shake userLoginAnim = new Shake(LoginField);
                Shake userPassAnim = new Shake(PasswordField);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
            }
        }
        public void OpenNewScene(String window){
            RegisterButton.getScene().getWindow().hide();

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
