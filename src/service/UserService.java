package service;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import model.ShopProduct;
import model.User;

import java.io.*;
import java.util.List;

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

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public void buyItem(User user, ShopProduct product, int quantity) {
        user.buyItem(product, quantity);
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
}
