package service;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import model.*;
import model.User;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }


//    public void saveUsersToFile(List<User> users, String filename) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(users);
//            System.out.println("Users saved to file: " + filename);
//        } catch (IOException e) {
//            System.err.println("Error saving users to file: " + e.getMessage());
//        }
//    }
//
//    public List<User> loadUsersFromFile(String filename) {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            List<User> loadedUsers = (List<User>) ois.readObject();
//            userDAO.getAllUsers().clear(); // Clear existing users
//            userDAO.getAllUsers().addAll(loadedUsers); // Add loaded users
//            System.out.println("Users loaded from file: " + filename);
//            return loadedUsers;
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading users from file: " + e.getMessage());
//            return null;
//        }
//    }

    public User addUser(Scanner scanner) {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter user email:");
        String email = scanner.nextLine();
        System.out.println("Enter user password:");
        String password = scanner.nextLine();
        User user = new User(name, email, password);
        userDAO.addUser(user);
        return user;
    }

    public void readUsers() {
        List<User> users = userDAO.getAllUsers();
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    public User enterUser(Scanner scanner) {
        System.out.println("Enter your user email:");
        String userEmail = scanner.nextLine();
        User user = userDAO.getUserByEmail(userEmail);
        if (user != null) {
            System.out.println("Welcome, " + user.getName());
        } else {
            System.out.println("User not found.");
            return null;
        }
        return user;
    }

    public void buyItem(User user, ShopProduct productToBuy, Scanner scanner) {

        System.out.println("Enter the quantity to buy:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (productToBuy.getQuantity() >= quantity && user.getBalance() >= (productToBuy.getPriceSell() * quantity)) {
            productToBuy.setQuantity(productToBuy.getQuantity() - quantity);
            deductBalance(user, productToBuy.getPriceSell() * quantity);
            Shop.getInstance().setBalance(productToBuy.getPriceSell() * quantity + Shop.getInstance().getBalance());
        } else {
            System.out.println("Insufficient quantity or balance.");
            System.out.println("Do you want to add the product to wishlist?(yes/no)");
            String addWishList = scanner.nextLine().toLowerCase();
            if (addWishList.equals("yes")){
                user.getWishList().addProduct(productToBuy, quantity);
            }
            else {
                return;
            }

        }
    }


    public void updateUser(User user, Scanner scanner) {
        System.out.println("Do you want to update the name? (yes/no)");
        String updateName = scanner.nextLine().toLowerCase();
        if (updateName.equals("yes")) {
            updateName(user, scanner);
        }

        System.out.println("Do you want to update the email? (yes/no)");
        String updateEmail = scanner.nextLine().toLowerCase();
        if (updateEmail.equals("yes")) {
            updateEmail(user, scanner);
        }

        System.out.println("Do you want to update the password? (yes/no)");
        String updatePassword = scanner.nextLine().toLowerCase();
        if (updatePassword.equals("yes")) {
            updatePassword(user, scanner);
        }
        userDAO.updateUser(user);
    }

    public void updateName(User user, Scanner scanner){
        System.out.println("Enter new name:");
        String newName = scanner.nextLine();
        user.setName(newName);
        System.out.println("Name updated successfully.");
    }

    public void updateEmail(User user, Scanner scanner){
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        user.setEmail(newEmail);
        System.out.println("Email updated successfully.");
    }

    public void updatePassword(User user, Scanner scanner){
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password updated successfully.");
    }


    public void addBalance(User user, Scanner scanner) {
        System.out.println("Enter amount to add:");
        int amount = scanner.nextInt();
        scanner.nextLine();
        user.setBalance(user.getBalance() + amount);
        userDAO.updateUser(user);
        System.out.println("Balance added successfully. Current balance: " + user.getBalance());
    }

    public void readWishlist(User user, Scanner scanner) {
        System.out.println("Wishlist:");

        Map<Product, Integer> products = user.getWishList().getProducts();
        if (products.isEmpty()) {
            System.out.println("Your wishlist is empty.");
        } else {
            int index = 1;
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(index + ". " + product.toString() + " - Quantity: " + quantity);
                index++;
            }
        }
    }

    public void deductBalance(User user, float balance){
        user.setBalance(user.getBalance() - balance);
        userDAO.updateUser(user);
    }
}
