package daoImpl;

import dao.UserDAO;
import model.Admin;
import model.Product;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();
    private String filename = "dataFiles/user_data.txt";


    public UserDAOImpl() {
        loadUsersFromFile();
    }
    @Override
    public List<User> getAllUsers() {
        loadUsersFromFile();
        return users;
    }


    @Override
    public User getUserById(int userId) {
        loadUsersFromFile();
        for (User user : users){
            if (user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    @Override
    public void updateUser(User newUser) {
        for (User user : users){
            if (user.getUserId() == newUser.getUserId()){
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setBalance(newUser.getBalance());
                saveUsersToFile();
                return;
            }
        }
    }

    @Override
    public void deleteUser(int userId) {
        for (User user : users){
            if (user.getUserId() == userId){
                users.remove(user);
                saveUsersToFile();
                return;
            }
        }
    }


    public User getUserByEmail(String email) {
        loadUsersFromFile();
        for (User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    public void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

}
