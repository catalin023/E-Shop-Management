package daoImpl;

import dao.AdminDAO;
import model.Admin;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM ADMIN";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return admins;
    }

    @Override
    public Admin getAdminById(int adminId) throws SQLException {
        String query = "SELECT * FROM ADMIN WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            rs = statement.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public Admin getAdminByEmail(String email) throws SQLException {
        String query = "SELECT * FROM ADMIN WHERE email=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO ADMIN (name, email, password) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPassword());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateAdmin(Admin newAdmin) throws SQLException {
        String sql = "UPDATE ADMIN SET name=?, email=?, password=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newAdmin.getName());
            statement.setString(2, newAdmin.getEmail());
            statement.setString(3, newAdmin.getPassword());
            statement.setInt(4, newAdmin.getAdminId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteAdmin(int adminId) throws SQLException {
        String sql = "DELETE FROM ADMIN WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();
        }
    }
}
