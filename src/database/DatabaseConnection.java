package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.DatabaseLoginData.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private static Connection connection = null;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_DRIVER, JDBC_USER, JDBC_PWD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
