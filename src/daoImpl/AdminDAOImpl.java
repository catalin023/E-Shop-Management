package daoImpl;

import dao.AdminDAO;
import model.Admin;

import java.io.*;
import java.util.ArrayList;
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
            if (admin.getEmail().equals(email)){
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
        for (Admin admin : admins){
            if (admin.getAdminId() == adminId){
                admins.remove(admin);
                return;
            }
        }
    }


//    public void saveAdminsToFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(admins);
//            System.out.println("Admins saved to file: " + filename);
//        } catch (IOException e) {
//            System.err.println("Error saving admins to file: " + e.getMessage());
//        }
//    }
//
//    public void loadAdminsFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            admins = (List<Admin>) ois.readObject();
//            System.out.println("Admins loaded from file: " + filename);
//            return;
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading admins from file: " + e.getMessage());
//            return;
//        }
//    }

}
