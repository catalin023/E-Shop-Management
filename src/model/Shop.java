package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private static Shop instance;
    private List<ShopProduct> products;
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

    public List<ShopProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShopProduct> products) {
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

    public void addBalance(float balance){this.balance += balance;}

    public void buyItem(ShopProduct product, int quantity, User user) {
        if (products.contains(product)) {
            if (product.getQuantity() >= quantity && user.getBalance() >= (product.getPriceSell() * quantity)) {
                product.setQuantity(product.getQuantity() - quantity);
                user.deductBalance(product.getPriceSell() * quantity);
                addBalance(product.getPriceSell() * quantity);
            } else {
                System.out.println("Insufficient quantity or balance.");
            }
        } else {
            System.out.println("Product not available in the shop.");
        }
    }


    public void addProduct(ShopProduct product){
        products.add(product);
    }

    public List<ShopProduct> filterProductsByCategory(String category) {
        List<ShopProduct> filteredProducts = new ArrayList<>();
        for (ShopProduct product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void sortProductsByPrice() {
        products.sort(Comparator.comparingInt(ShopProduct::getPriceSell));
    }

    public List<ShopProduct> viewLowStockProducts(int threshold) {
        List<ShopProduct> lowStockProducts = new ArrayList<>();
        for (ShopProduct product : products) {
            if (product.getQuantity() <= threshold) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }



}
