package service;

import dao.AdminDAO;
import daoImpl.AdminDAOImpl;
import model.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAOImpl();
    }

    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }


    public void deleteAdmin(int adminId) {
        adminDAO.deleteAdmin(adminId);
    }

    public Admin addAdmin(Scanner scanner) {
        System.out.println("Enter admin name:");
        String name = scanner.nextLine();
        System.out.println("Enter admin email:");
        String email = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();
        Admin admin = new Admin(name, email, password);
        adminDAO.addAdmin(admin);
        return admin;
    }

    public Admin enterAdmin(Scanner scanner) {
        System.out.println("Enter your admin email:");
        String adminEmail = scanner.nextLine();
        Admin admin = adminDAO.getAdminByEmail(adminEmail);
        if (admin != null) {
            System.out.println("Welcome, " + admin.getName());
        } else {
            System.out.println("Admin not found.");
            return null;
        }
        return admin;
    }

    public void readAdmins() {
        List<Admin> admins = adminDAO.getAllAdmins();
        System.out.println("List of Admins:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }



    public void updateAdmin(Admin admin, Scanner scanner) {
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
