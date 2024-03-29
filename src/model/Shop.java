package model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static Shop instance;
    private List<Product> products;
    private List<Admin> admins;
    private List<User> users;
    private float balance;

    private Shop() {
        this.products = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static Shop getInstance(){
        if (instance == null){
            instance = new Shop();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
