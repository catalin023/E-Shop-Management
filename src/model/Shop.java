package model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static Shop instance;
    private List<Product> products;
    private Admin admin;
    private List<User> users;

    private Shop() {
        this.products = new ArrayList<>();
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
