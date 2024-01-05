package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static DbUtil instance;
    private Connection connection;

    private DbUtil() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DbUtil getInstance() {
        if (instance == null) {
            instance = new DbUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
