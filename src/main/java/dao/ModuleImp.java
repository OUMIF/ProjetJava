package dao;

import model.User;
import model.Module;
import util.DatabaseConnection;
import util.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class ModuleImp {


    Connection co = DatabaseConnection.getInstance().getConnection();
    User ue = Session.getCurrentUser();


    public List<Module> getAll(){

        List<Module> modules = null ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM modules";
        try {
            ps = co.prepareStatement(sql);
            rs = ps.executeQuery();
            modules = new ArrayList<Module>();
            while (rs.next()){

                Module m = new Module(rs.getInt(1),rs.getString(2),rs.getString(3));
                modules.add(m);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return modules;
    }

    public Optional<Module> getById(int id){

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM modules WHERE id = ?";
        try {

            ps = co.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                Module m = new Module(rs.getInt(1),rs.getString(2),rs.getString(3));
                return Optional.of(m);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public boolean AjouterModule(Module m, User ue) {
        String sqlModule = "INSERT INTO modules (nomModule, codeModule) VALUES (?, ?)";
        String sqlAjouteModule = "INSERT INTO ajoutemodule (idmodule, iduser, dateajout) VALUES (?, ?, ?)";

        try (PreparedStatement psModule = co.prepareStatement(sqlModule, Statement.RETURN_GENERATED_KEYS)) {
            // Insertion dans la table "modules"
            psModule.setString(1, m.getNomModule());
            psModule.setString(2, m.getCodeModule());
            int affectedRows = psModule.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = psModule.getGeneratedKeys()) {
                    if (rs.next()) {
                        int moduleId = rs.getInt(1);

                        // Insertion dans la table "ajoutemodule"
                        try (PreparedStatement psAjouteModule = co.prepareStatement(sqlAjouteModule)) {
                            psAjouteModule.setInt(1, moduleId);
                            psAjouteModule.setInt(2, ue.getId());
                            psAjouteModule.setDate(3, Date.valueOf(LocalDate.now()));

                            int affectedRowsAjout = psAjouteModule.executeUpdate();
                            return affectedRowsAjout > 0;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding module: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteModule(int id) {
        String sqlDeleteAjouteModule = "DELETE FROM ajoutemodule WHERE idmodule = ?";
        String sqlDeleteModule = "DELETE FROM modules WHERE idmodule = ?";

        try (PreparedStatement psAjouteModule = co.prepareStatement(sqlDeleteAjouteModule);
             PreparedStatement psModule = co.prepareStatement(sqlDeleteModule)) {

            // Suppression dans la table "ajoutemodule"
            psAjouteModule.setInt(1, id);
            psAjouteModule.executeUpdate();

            // Suppression dans la table "modules"
            psModule.setInt(1, id);
            int affectedRows = psModule.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting module: " + e.getMessage());
        }

        return false;
    }




}
