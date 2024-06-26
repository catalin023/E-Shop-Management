package service;

import daoImpl.AdminDAOImpl;
import model.*;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class AdminService {
    private AdminDAOImpl adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAOImpl();
    }


    public void deleteAdmin(int adminId) throws SQLException {
        adminDAO.delete(adminId);
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete admin " + adminId);
    }

    public Admin addAdmin(Scanner scanner) throws SQLException {
        System.out.println("Enter admin name:");
        String name = scanner.nextLine();
        System.out.println("Enter admin email:");
        String email = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();
        Admin admin = new Admin(name, email, password);
        adminDAO.create(admin);
        FileManagement.scriereFisierChar(AUDIT_FILE, "create admin " + name);
        admin = adminDAO.readByEmail(email);
        return admin;
    }

    public Admin enterAdmin(Scanner scanner) throws SQLException {
        System.out.println("Enter your admin email:");
        String adminEmail = scanner.nextLine();
        Admin admin = adminDAO.readByEmail(adminEmail);
        FileManagement.scriereFisierChar(AUDIT_FILE, "read admin " + adminEmail);
        if (admin != null) {
            System.out.println("Welcome, " + admin.getName());
        } else {
            System.out.println("Admin not found.");
            return null;
        }
        return admin;
    }

    public void readAdmins() throws SQLException {
        List<Admin> admins = adminDAO.read();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read admins");
        System.out.println("List of Admins:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }



    public void updateAdmin(Admin admin, Scanner scanner) throws SQLException {
        System.out.println("Do you want to update the name? (yes/no)");
        String updateName = scanner.nextLine().toLowerCase();
        if (updateName.equals("yes")) {
            updateName(admin, scanner);
        }

        System.out.println("Do you want to update the email? (yes/no)");
        String updateEmail = scanner.nextLine().toLowerCase();
        if (updateEmail.equals("yes")) {
            updateEmail(admin, scanner);
        }

        System.out.println("Do you want to update the password? (yes/no)");
        String updatePassword = scanner.nextLine().toLowerCase();
        if (updatePassword.equals("yes")) {
            updatePassword(admin, scanner);
        }

        adminDAO.update(admin);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update admin " + admin.getName());
    }

    public void updateName(Admin admin, Scanner scanner){
        System.out.println("Enter new name:");
        String newName = scanner.nextLine();
        admin.setName(newName);
        System.out.println("Name updated successfully.");
    }

    public void updateEmail(Admin admin, Scanner scanner){
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        admin.setEmail(newEmail);
        System.out.println("Email updated successfully.");
    }

    public void updatePassword(Admin admin, Scanner scanner){
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        admin.setPassword(newPassword);
        System.out.println("Password updated successfully.");
    }

}
