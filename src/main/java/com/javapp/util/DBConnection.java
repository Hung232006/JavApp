package com.javapp.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection{
private static final String URL = "jdbc:mysql://127.0.0.1:3306/Quan_Ly_DV?autoReconnect=true&useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "CSDL2021";

     @SuppressWarnings("CallToPrintStackTrace")
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}