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

    public Admin getAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    public void deleteAdmin(int adminId) {
        adminDAO.deleteAdmin(adminId);
    }

    public void restockItem(ShopProduct product, int quantity, Admin admin) {
        admin.restockItem(product, quantity);
    }

    public void setSellPrice(Product product, int priceSell, Admin admin) {
        admin.setSellPrice(product, priceSell);
    }

    public void addProductToShop(ShopProduct product, Admin admin) {
        admin.addProductToShop(product);
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
        }
        return admin;
    }

    public void readAdmins() {
        List<Admin> admins = getAllAdmins();
        System.out.println("List of Admins:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }


    public void updateProduct(Scanner scanner) {
        Shop.getInstance().readProducts();

        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Assuming you have a method to get all products from a data source
        List<ShopProduct> products = Shop.getInstance().getProducts();
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        ShopProduct productToUpdate = products.get(choice - 1);

        System.out.println("Enter the new price for the product:");
        Integer newPrice = Integer.valueOf(scanner.nextLine());
        productToUpdate.setPriceSell(newPrice);

        System.out.println("Product updated: " + productToUpdate.toString());
    }

    public void deleteProduct(Scanner scanner) {
        Shop.getInstance().readProducts();

        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Assuming you have a method to get all products from a data source
        List<ShopProduct> products = Shop.getInstance().getProducts();
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        ShopProduct productToDelete = products.remove(choice - 1);
        System.out.println("Product deleted: " + productToDelete.toString());
    }

    public void restockItem(DistributorService distributorService, Scanner scanner) {
        distributorService.readDistributors();

        System.out.println("Enter the number of the distributor to restock from:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Distributor> distributors = distributorService.getAllDistributors();
        if (choice < 1 || choice > distributors.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Distributor distributorRestockFrom = distributors.get(choice - 1);

        List<Product> products = distributorRestockFrom.getProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }

        System.out.println("Enter the number of the product to restock:");
        choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Product productToRestock = products.get(choice - 1);

        System.out.println("Enter the quantity to restock:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        ShopProduct productInShop = Shop.getInstance().getProductById(productToRestock.getProductId());

        if (productInShop == null){
            System.out.println("Enter the price to sell the product:");
            int priceSell = scanner.nextInt();
            scanner.nextLine();

            ShopProduct product = new ShopProduct(productToRestock, priceSell, quantity);
            Shop.getInstance().addProduct(product);
        }
        else {
            productInShop.setQuantity(productInShop.getQuantity() + quantity);
        }
        Shop.getInstance().setBalance(Shop.getInstance().getBalance()-quantity*productToRestock.getPriceBuy());
    }

    public void updateAdmin(Admin admin, Scanner scanner) {
        System.out.println("Do you want to update the name? (yes/no)");
        String updateName = scanner.nextLine().toLowerCase();
        if (updateName.equals("yes")) {
            System.out.println("Enter new name:");
            String newName = scanner.nextLine();
            admin.setName(newName);
            System.out.println("Name updated successfully.");
        }

        System.out.println("Do you want to update the email? (yes/no)");
        String updateEmail = scanner.nextLine().toLowerCase();
        if (updateEmail.equals("yes")) {
            System.out.println("Enter new email:");
            String newEmail = scanner.nextLine();
            admin.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        System.out.println("Do you want to update the password? (yes/no)");
        String updatePassword = scanner.nextLine().toLowerCase();
        if (updatePassword.equals("yes")) {
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            admin.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        }
    }


    public void saveAdminsToFile(List<Admin> admins, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(admins);
            System.out.println("Admins saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving admins to file: " + e.getMessage());
        }
    }

    public List<Admin> loadAdminsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Admin> loadedAdmins = (List<Admin>) ois.readObject();
            adminDAO.getAllAdmins().clear(); // Clear existing admins
            adminDAO.getAllAdmins().addAll(loadedAdmins); // Add loaded admins
            System.out.println("Admins loaded from file: " + filename);
            determineNextAdminId(loadedAdmins);
            return loadedAdmins;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading admins from file: " + e.getMessage());
            return null;
        }
    }

    private static void determineNextAdminId(List<Admin> admins) {
        int maxAdminId = 0;
        for (Admin admin : admins) {
            if (admin.getAdminId() > maxAdminId) {
                maxAdminId = admin.getAdminId();
            }
        }
        Admin.setNextAdmin(maxAdminId+1);
    }

}
