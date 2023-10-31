package com.majidim.easybankv4.easybankv4.helper;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/easybankv4";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private  static Connection connection;
    public static Connection getConn() {

        if(connection==null){

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }



        return connection;
    }




}
