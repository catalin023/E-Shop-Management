package service;

import daoImpl.UserDAOImpl;
import daoImpl.WishListDAOImpl;
import model.*;
import model.User;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class UserService {
    private final UserDAOImpl userDAO;
    private final WishListDAOImpl wishListDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
        this.wishListDAO = new WishListDAOImpl();
    }

    public void deleteUser(int userId) throws SQLException {
        wishListDAO.delete(userId);
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete wishlist " + userId);
        userDAO.delete(userId);
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete user " + userId);
    }

    public User addUser(Scanner scanner) throws SQLException {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter user email:");
        String email = scanner.nextLine();
        System.out.println("Enter user password:");
        String password = scanner.nextLine();
        User user = new User(name, email, password);
        userDAO.create(user);
        FileManagement.scriereFisierChar(AUDIT_FILE, "create user " + name);
        user = userDAO.readByEmail(email);
        return user;
    }

    public void readUsers() throws SQLException {
        List<User> users = userDAO.read();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read users");
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    public User enterUser(Scanner scanner) throws SQLException {
        System.out.println("Enter your user email:");
        String userEmail = scanner.nextLine();
        User user = userDAO.readByEmail(userEmail);
        if (user != null) {
            System.out.println("Welcome, " + user.getName());
        } else {
            System.out.println("User not found.");
            return null;
        }
        FileManagement.scriereFisierChar(AUDIT_FILE, "read user " + user.getName());
        return user;
    }

    public void buyItem(User user, ShopProductService shopProductService, ShopService shopService, Scanner scanner) throws SQLException {
        ShopProduct productToBuy = shopProductService.chooseItem(scanner);
        if (productToBuy == null) {
            return;
        }

        System.out.println("Enter the quantity to buy:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (productToBuy.getQuantity() >= quantity && user.getBalance() >= (productToBuy.getPriceSell() * quantity)) {
            shopProductService.deductQuantity(productToBuy, quantity);
            deductBalance(user, productToBuy.getPriceSell() * quantity);
            Shop.getInstance().setBalance(productToBuy.getPriceSell() * quantity + shopService.getShop());
            shopService.updateShop(Shop.getInstance());
        } else {
            System.out.println("Insufficient quantity or balance.");
            System.out.println("Do you want to add the product to wishlist?(yes/no)");
            String addWishList = scanner.nextLine().toLowerCase();
            if (addWishList.equals("yes")){
                addToWishlist(user, productToBuy, quantity);
            }
        }
    }

    public void addToWishlist(User user, ShopProduct shopProduct, int quantity) throws SQLException {
        Map<ShopProduct, Integer> products = wishListDAO.read(user.getUserId()).getProducts();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read wishlist " + user.getUserId());
        if (products.containsKey(shopProduct)) {
            wishListDAO.update(user.getUserId(), shopProduct.getId(), quantity);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update wishlist " + user.getUserId());
        } else {
            wishListDAO.create(user.getUserId(), shopProduct.getId(), quantity);
            FileManagement.scriereFisierChar(AUDIT_FILE, "create wishlist " + user.getUserId());
        }

    }


    public void updateUser(User user, Scanner scanner) throws SQLException {
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
        userDAO.update(user);
        FileManagement.scriereFisierChar(AUDIT_FILE, "udate user " + user.getName());
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


    public void addBalance(User user, Scanner scanner) throws SQLException {
        System.out.println("Enter amount to add:");
        int amount = scanner.nextInt();
        scanner.nextLine();
        user.setBalance(user.getBalance() + amount);
        userDAO.update(user);
        System.out.println("Balance added successfully. Current balance: " + user.getBalance());
        FileManagement.scriereFisierChar(AUDIT_FILE, "update user " + user.getName());
    }

    public void readWishlist(User user, Scanner scanner) throws SQLException {
        System.out.println("Wishlist:");

        Map<ShopProduct, Integer> products = wishListDAO.read(user.getUserId()).getProducts();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read wishlist " + user.getUserId());
        if (products.isEmpty()) {
            System.out.println("Your wishlist is empty.");
        } else {
            int index = 1;
            for (Map.Entry<ShopProduct, Integer> entry : products.entrySet()) {
                ShopProduct product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(index + ". " + product.toString() + " - Quantity: " + quantity);
                index++;
            }
        }
    }

    public void deductBalance(User user, float balance) throws SQLException {
        user.setBalance(user.getBalance() - balance);
        userDAO.update(user);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update user " + user.getName());
    }
}
