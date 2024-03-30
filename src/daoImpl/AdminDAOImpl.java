package daoImpl;

import dao.AdminDAO;
import model.Admin;
import model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private List<Admin> admins = new ArrayList<>();
    @Override
    public List<Admin> getAllAdmins() {
        return admins;
    }

    @Override
    public Admin getAdminById(int adminId) {
        for (Admin admin : admins){
            if (admin.getAdminId() == adminId){
                return admin;
            }
        }
        return null;
    }

    public Admin getAdminByEmail(String email) {
        for (Admin admin : admins){
            if (admin.getEmail() == email){
                return admin;
            }
        }
        return null;
    }

    @Override
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    @Override
    public void updateAdmin(Admin newAdmin) {
        for (Admin admin : admins){
            if (admin.getAdminId() == newAdmin.getAdminId()){
                admin.setName(newAdmin.getName());
                admin.setEmail(newAdmin.getEmail());
                admin.setPassword(newAdmin.getPassword());
                return;
            }
        }
    }

    @Override
    public void deleteAdmin(int adminId) {
        Iterator<Admin> iterator = admins.iterator();
        while (iterator.hasNext()) {
            Admin admin = iterator.next();
            if (admin.getAdminId() == adminId) {
                iterator.remove();
                return;
            }
        }
    }
}
