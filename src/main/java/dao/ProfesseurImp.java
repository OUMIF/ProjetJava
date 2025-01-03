package dao;

import model.Etudiant;
import model.Module;
import model.Professeur;
import model.User;
import util.DatabaseConnection;
import util.Session;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Optional;

public class ProfesseurImp {

    Connection co = DatabaseConnection.getInstance().getConnection();
    User ue = Session.getCurrentUser();


    public List<Etudiant> listeEtudiants(Integer id){
        List<Etudiant>etudiants = null;
        Etudiant etudiant;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Sql = " SELECT * \n" +
                "FROM etudiants e\n" +
                "JOIN inscrire i ON e.idetudiant = i.idetudiant\n" +
                "JOIN modules m ON m.idmodule = i.idmodule\n" +
                "JOIN assigner a ON a.idmodule = m.idmodule\n" +
                "JOIN professeurs p ON p.iduser = a.iduser\n" +
                "WHERE p.iduser = ?;\n ";

        try{
            etudiants = new ArrayList<Etudiant>();
            ps = co.prepareStatement(Sql);
            ps.setInt(ue.getId(),id);
            rs = ps.executeQuery();
            while (rs.next()){
                etudiant = new Etudiant(rs.getString(6),rs.getString(2),rs.getString(5),rs.getString(4),rs.getString(3),rs.getInt(1));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return etudiants;

    }

    public List<Module> getModuleAssigner(Integer id){
        Module md = null;
        List<Module>modules = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Sql = "SELECT * \n" +
                "FROM modules m\n" +
                "JOIN assigner a ON a.idmodule = m.idmodule\n" +
                "JOIN professeurs p ON p.iduser = a.iduser\n" +
                "WHERE p.iduser = ?;\n";

        try {
            modules = new ArrayList<>();
            ps = co.prepareStatement(Sql);
            ps.setInt(ue.getId(),id);
            rs = ps.executeQuery();
            while (rs.next()){
                md = new Module();
                md.setCodeModule(rs.getString(3));
                md.setNomModule(rs.getString(2));
                md.setId(rs.getInt(1));
                modules.add(md);

            }
            return modules;


        } catch (SQLException e){
            System.out.println(e.getMessage());

        }
        return null;
    }

    public List<Etudiant> searchEtudiantByName(String name) {
        List<Etudiant> etudiants = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM etudiants e " +
                "JOIN inscrire i ON e.idetudiant = i.idetudiant " +
                "JOIN modules m ON m.idmodule = i.idmodule " +
                "JOIN assigner a ON a.idmodule = m.idmodule " +
                "JOIN professeurs p ON p.iduser = a.iduser " +
                "WHERE e.nom LIKE ?;";  // Recherche par nom

        try {
            etudiants = new ArrayList<>();
            ps = co.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");  // Recherche partielle avec LIKE
            rs = ps.executeQuery();
            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getString(6),  // Matricule
                        rs.getString(2),  // Nom
                        rs.getString(5),  // Prénom
                        rs.getString(4),  // Date de naissance
                        rs.getString(3),  // Promotion
                        rs.getInt(1)      // Id
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return etudiants;
    }

    public int getNombreModules(int professeurId) {
        int moduleCount = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) AS module_count " +
                "FROM modules m " +
                "JOIN assigner a ON a.idmodule = m.idmodule " +
                "JOIN professeurs p ON p.iduser = a.iduser " +
                "WHERE p.iduser = ?";
        try {
            ps = co.prepareStatement(query);
            ps.setInt(ue.getId(), professeurId);
            rs = ps.executeQuery();
            if (rs.next()) {
                moduleCount = rs.getInt("module_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return moduleCount;
    }

    public int getNombreEtudiants(int professeurId) {
        int studentCount = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(DISTINCT e.idetudiant) AS student_count " +
                "FROM etudiants e " +
                "JOIN inscrire i ON e.idetudiant = i.idetudiant " +
                "JOIN modules m ON i.idmodule = m.idmodule " +
                "JOIN assigner a ON a.idmodule = m.idmodule " +
                "JOIN professeurs p ON p.iduser = a.iduser " +
                "WHERE p.iduser = ?";
        try {
            ps = co.prepareStatement(query);
            ps.setInt(ue.getId(), professeurId);
            rs = ps.executeQuery();
            if (rs.next()) {
                studentCount = rs.getInt("student_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentCount;
    }

    public boolean AjouterProf(Professeur pf , User user) {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        String Pass =  generateRandomPassword();
        String Sql = "Insert Into users(email,password,role) VALUES (?,?,3)";
        String Sql2 = "INSERT INTO professeurs(iduser,nom,prenom,specialite) VALUES (?,?,?,?)";

        try{
            ps1 = co.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1,user.getEmail());
            ps1.setString(2,Pass);
            int l = ps1.executeUpdate();
                if (l> 0 ){
                    ResultSet rs = ps1.getGeneratedKeys();
                    if (rs.next()){
                        int id = rs.getInt(1);

                    ps2 = co.prepareStatement(Sql2);
                    ps2.setInt(1,id);
                    ps2.setString(2,pf.getNom());
                    ps2.setString(3,pf.getPrenom());
                    ps2.setString(4,pf.getSpecialite());
                    int l2 = ps2.executeUpdate();
                        if (l2 > 0) {
                            return true;  // Si l'insertion du professeur réussit
                        }
                    }
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
            } catch (SQLException e) {
               System.out.println(e.getMessage());
            }
        }
        return false;
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {  // Longueur du mot de passe temporaire
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }



}