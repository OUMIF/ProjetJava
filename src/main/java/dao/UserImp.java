package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.User;
import util.DatabaseConnection;

public class UserImp {

    public User  getUserById(int id) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        User us = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from users where id = ?";
        try{
            ps = conn.prepareStatement(sql,1);
            rs = ps.executeQuery();
            while (rs.next())
            {
                us = new User(rs.getInt(1),rs.getString(2));
            }
            return us;
        } catch (SQLException e){
            System.out.println("Une erreur est survenue" + e.getMessage());
        }
        return null;

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f41a29cbd0b00152a7dd0b9638cb677dd53b68b3
