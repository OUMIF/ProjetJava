package dao;

import model.Etudiant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImp {

    private final Connection connection;


    public EtudiantImp(Connection connection) {
        this.connection = connection;
    }

    // Insert a new student into the database
    public int insertEtudiant(String matricule, String nom, String prenom, String promotion, String dateNaissance) throws SQLException {
        String sql = "INSERT INTO etudiants (matricule, nom, prenom, datenaissance, promotion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, matricule);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setDate(4, Date.valueOf(dateNaissance));
            stmt.setString(5, promotion);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return generated ID
                } else {
                    throw new SQLException("Failed to insert student, no ID obtained.");
                }
            }
        }
    }

    // Check if a student exists based on matricule
    public boolean isStudentExists(String matricule) throws SQLException {
<<<<<<< HEAD
        String sql = "SELECT COUNT(*) FROM Etudiants WHERE matricule = ?";
=======
        String sql = "SELECT COUNT(*) FROM etudiants WHERE matricule = ?";
>>>>>>> 1513327bb9ac94b5b98da58c44743c56381a5576
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matricule);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; // Return true if student exists
            }
        }
    }

    // Retrieve all students from the database
    public List<Etudiant> getAllStudents() throws SQLException {
        String sql = "SELECT idetudiant, matricule, nom, prenom, datenaissance, promotion FROM etudiants";
        List<Etudiant> students = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Etudiant student = new Etudiant(
                        rs.getString("Promotion"),
                        rs.getString("Matricule"),
                        rs.getString("DateNaissance"),
                        rs.getString("Prenom"),
                        rs.getString("Nom"),
                        rs.getInt("IdEtudiant") // Use 'IdEtudiant' instead of 'id'
                );
                students.add(student);
            }
        }
        return students;
    }

    // Delete a student by ID
    public boolean deleteStudentById(int id) throws SQLException {
<<<<<<< HEAD
        String sql = "DELETE FROM etudiants WHERE idetudiant = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // Return true if a row was deleted
=======
        String deleteInscrire = "DELETE FROM inscrire WHERE idetudiant = ?";
        String deleteGererEtudiant = "DELETE FROM gereretudiant WHERE idetudiant = ?";
        String deleteEtudiant = "DELETE FROM Etudiants WHERE idetudiant = ?";

        try (PreparedStatement stmtInscrire = connection.prepareStatement(deleteInscrire);
             PreparedStatement stmtGerer = connection.prepareStatement(deleteGererEtudiant);
             PreparedStatement stmtEtudiant = connection.prepareStatement(deleteEtudiant)) {

            connection.setAutoCommit(false); // Start transaction

            // Delete from inscrire
            stmtInscrire.setInt(1, id);
            stmtInscrire.executeUpdate();

            // Delete from gereretudiant
            stmtGerer.setInt(1, id);
            stmtGerer.executeUpdate();

            // Delete from Etudiants
            stmtEtudiant.setInt(1, id);
            boolean result = stmtEtudiant.executeUpdate() > 0;

            connection.commit(); // Commit transaction
            return result;
        } catch (SQLException ex) {
            connection.rollback(); // Rollback on failure
            throw ex;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit
>>>>>>> 1513327bb9ac94b5b98da58c44743c56381a5576
        }
    }

    public void updateStudent(Etudiant student) throws SQLException {
<<<<<<< HEAD
=======
        // Disable auto-commit if it's turned off
        connection.setAutoCommit(false);

>>>>>>> 1513327bb9ac94b5b98da58c44743c56381a5576
        String query = "UPDATE etudiants SET matricule = ?, nom = ?, prenom = ?, promotion = ? WHERE idetudiant = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getMatricule());
            stmt.setString(2, student.getNom());
            stmt.setString(3, student.getPrenom());
            stmt.setString(4, student.getPromotion());
            stmt.setInt(5, student.getId());
            stmt.executeUpdate();

            // Commit the transaction after the update
            connection.commit();
        } catch (SQLException e) {
            // Rollback in case of an error
            connection.rollback();
            throw e;
        } finally {
            // Reset auto-commit to true (if it was previously false)
            connection.setAutoCommit(true);
        }
    }


}
