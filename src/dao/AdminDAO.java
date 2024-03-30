package dao;

import model.Admin;

import java.util.List;

public interface AdminDAO {
    List<Admin> getAllAdmins();
    Admin getAdminById(int adminId);
    Admin getAdminByEmail(String email);
    void addAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void deleteAdmin(int adminId);
}