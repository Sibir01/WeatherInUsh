package com.example.weatherinush; // Этот класс создан для подключения к базе даных

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    //прописываем подключение к БД
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    // Добавляем информацию о погоде в БД
    public void addInfoInData(String Day, String Felt, String Precipitation, String Temperature) {
        String insert = "INSERT " + Const.DATA_TABLE + "(" + Const.DATA_DAY + "," +
                Const.DATA_FELT + "," + Const.DATA_PRECIPITATION + "," +
                Const.DATA_TEMPERATURE + ")" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,Day);
            prSt.setString(2,Temperature);
            prSt.setString(3,Felt);
            prSt.setString(4,Precipitation);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

// Пишем скюл запрос для добавления пользоввателя в бд
    public void signUpUser(String Name, String LastName, String Login, String Password) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME +"," +
                Const.USER_LASTNAME + "," + Const.USER_LOGIN + "," +
                Const.USER_PASSWORD + ")" + "VALUES(?,?,?,?)";

        try {
        //выполняем запрос
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1,Name);
        prSt.setString(2,LastName);
        prSt.setString(3,Login);
        prSt.setString(4,Password);


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) { //метод для возвращение даных из Базы Даных
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getName()); //ужно сделать User
            prSt.setString(2,user.getPassword());


            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;

    }



}
