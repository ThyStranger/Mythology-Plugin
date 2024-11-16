package com.Stranger.Mythology.PlayerDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    private final String HOST="localhost";
    private final int PORT = 3306;
    private final String DATABSE = "mythology_player_stats";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public void connect() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DATABSE+"?useSSL=false",USERNAME,PASSWORD);

    }

    public boolean isConnected(){
        return connection!=null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect(){
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Database not connected, cannot disconnect");
        }
    }
}
