package dao;

import model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    List<Admin> getAllAdmins() throws SQLException;
    Admin getAdminById(int adminId) throws SQLException;
    Admin getAdminByEmail(String email) throws SQLException;
    void addAdmin(Admin admin) throws SQLException;
    void updateAdmin(Admin admin) throws SQLException;
    void deleteAdmin(int adminId) throws SQLException;
}