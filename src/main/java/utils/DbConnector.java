package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/store";
        String username = "root";
        String password = "Marvel@2022";
        return DriverManager.getConnection(url, username, password);
    }
}

