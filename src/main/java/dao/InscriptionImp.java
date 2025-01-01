package dao;

import util.Session;

import java.sql.*;

public class InscriptionImp {

    private final Connection connection;

    public InscriptionImp(Connection connection) {
        this.connection = connection;
    }

    public void insertInscription(int idEtudiant, String moduleName) throws SQLException {
<<<<<<< HEAD
        String sql = "INSERT INTO inscrire (idetudiant, idmodule, dateinscription) " +
=======
        String sql = "INSERT INTO Inscrire (idetudiant, idmodule, dateinscription) " +
>>>>>>> 1513327bb9ac94b5b98da58c44743c56381a5576
                "VALUES (?, (SELECT idmodule FROM modules WHERE nommodule = ?), CURRENT_DATE)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEtudiant);
            stmt.setString(2, moduleName);

            stmt.executeUpdate();
        }
    }
}
