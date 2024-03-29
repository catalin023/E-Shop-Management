package daoImpl;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();


    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(int userId) {
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
    }

    @Override
    public void updateUser(User newUser) {
        for (User user : users){
            if (user.getUserId() == newUser.getUserId()){
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setBalance(newUser.getBalance());
                return;
            }
        }
    }

    @Override
    public void deleteUser(int userId) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserId() == userId) {
                iterator.remove();
                return;
            }
        }
    }
}
