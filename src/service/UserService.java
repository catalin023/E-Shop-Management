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

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public void addToWishlist(User user, ShopProduct product, int quantity) {
        user.addToWishlist(product, quantity);
    }

    public void viewWishlist(User user) {
        user.viewWishlist();
    }



    public void saveUsersToFile(List<User> users, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
            System.out.println("Users saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }
    }

    public List<User> loadUsersFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<User> loadedUsers = (List<User>) ois.readObject();
            userDAO.getAllUsers().clear(); // Clear existing users
            userDAO.getAllUsers().addAll(loadedUsers); // Add loaded users
            System.out.println("Users loaded from file: " + filename);
            return loadedUsers;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
            return null;
        }
    }

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
        List<User> users = getAllUsers();
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
        }
        return user;
    }

    public void buyItem(User user, Scanner scanner) {
        System.out.println("View all/phone/TV/laptop/price");
        String command = scanner.nextLine();
        List<ShopProduct> products;
        switch (command){
            case "all":
                products = Shop.getInstance().getProducts();
                break;
            case "price":
                products = Shop.getInstance().getProducts();
                products.sort(Comparator.comparingInt(ShopProduct::getPriceSell));
                break;
            default:
                products = Shop.getInstance().filterProductsByCategory(command);
                break;
        }

        System.out.println("List of Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
        System.out.println("Enter the number of the product to buy:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        ShopProduct productToBuy = products.get(choice - 1);

        System.out.println("Enter the quantity to buy:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Shop.getInstance().buyItem(productToBuy, quantity, user, scanner);
    }

    public void updateUser(User user, Scanner scanner) {
        System.out.println("Do you want to update the name? (yes/no)");
        String updateName = scanner.nextLine().toLowerCase();
        if (updateName.equals("yes")) {
            System.out.println("Enter new name:");
            String newName = scanner.nextLine();
            user.setName(newName);
            System.out.println("Name updated successfully.");
        }

        System.out.println("Do you want to update the email? (yes/no)");
        String updateEmail = scanner.nextLine().toLowerCase();
        if (updateEmail.equals("yes")) {
            System.out.println("Enter new email:");
            String newEmail = scanner.nextLine();
            user.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        System.out.println("Do you want to update the password? (yes/no)");
        String updatePassword = scanner.nextLine().toLowerCase();
        if (updatePassword.equals("yes")) {
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            user.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        }
    }


    public void addBalance(User user, Scanner scanner) {
        System.out.println("Enter amount to add:");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        user.addBalance(amount);
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
}
