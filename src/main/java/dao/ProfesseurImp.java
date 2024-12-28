package dao;

import model.Etudiant;
import model.Module;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesseurImp {

    Connection co = DatabaseConnection.getInstance().getConnection();


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
            ps.setInt(1,id);
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
            ps.setInt(1,id);
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
}