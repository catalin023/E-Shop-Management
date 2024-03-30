package service;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import model.ShopProduct;
import model.User;

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
}
