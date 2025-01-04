package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/ProjetJavaa";  // Make sure the URL is correct for your database
    private final String user = "root";
    private final String password = "";

    private DatabaseConnection() {
        try {
            // Load MySQL JDBC driver
            System.out.println("Loading driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");

        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
