package dao;

import model.User;
import util.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GererEtudiantIMP {

    private Connection connection;

    public GererEtudiantIMP(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new record into GererEtudiant
    public void insertGererEtudiant(int idEtudiant) throws SQLException {
        User currentUser = Session.getCurrentUser();

        if (currentUser == null) {
            throw new SQLException("No user is logged in.");
        }

        int idUserSecretaire = getIdUserSecretaire(currentUser);

        String query = "INSERT INTO gereretudiant (idusersecretaire, idetudiant) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUserSecretaire);
            statement.setInt(2, idEtudiant);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Log error or rethrow the exception
            throw new SQLException("Failed to insert GererEtudiant record.", e);
        }
    }

    // Method to get IdUserSecretaire from the current logged-in user
    private int getIdUserSecretaire(User currentUser) throws SQLException {
        String query = "SELECT iduser FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Log the email being queried for debugging purposes
            System.out.println("Querying for user with email: " + currentUser.getEmail());

            statement.setString(1, currentUser.getEmail());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Return the IdUser from Users table
                    return resultSet.getInt("iduser");
                } else {
                    // No user found with the provided email
                    throw new SQLException("User not found for email: " + currentUser.getEmail());
                }
            }
        } catch (SQLException e) {
            // Log error or rethrow the exception
            throw new SQLException("Failed to retrieve User ID for email: " + currentUser.getEmail(), e);
        }
    }
}
