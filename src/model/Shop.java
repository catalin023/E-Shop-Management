package model;

import java.io.*;
import java.util.*;

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

    public void buyItem(ShopProduct product, int quantity, User user, Scanner scanner) {
        if (products.contains(product)) {
            if (product.getQuantity() >= quantity && user.getBalance() >= (product.getPriceSell() * quantity)) {
                product.setQuantity(product.getQuantity() - quantity);
                user.deductBalance(product.getPriceSell() * quantity);
                addBalance(product.getPriceSell() * quantity);
            } else {
                System.out.println("Insufficient quantity or balance.");
                System.out.println("Do you want to add the product to wishlist?(yes/no)");
                String addWishList = scanner.nextLine().toLowerCase();
                if (addWishList.equals("yes")){
                    user.getWishList().addProduct(product, quantity);
                }
                else {
                    return;
                }

            }
        } else {
            System.out.println("Product not available in the shop.");
        }
    }


    public void addProduct(ShopProduct product){
        products.add(product);
    }


    public void readProducts() {
        System.out.println("List of Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
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

    public void loadDataFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Load products
            this.products = (List<ShopProduct>) ois.readObject();
            // Load admins
            this.admins = (List<Admin>) ois.readObject();
            // Load users
            this.users = (List<User>) ois.readObject();
            // Load balance
            this.balance = ois.readFloat();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    public void saveDataToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Save products
            oos.writeObject(this.products);
            // Save admins
            oos.writeObject(this.admins);
            // Save users
            oos.writeObject(this.users);
            // Save balance
            oos.writeFloat(this.balance);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public ShopProduct getProductById(int productId) {
        for (ShopProduct product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null; // Product not found
    }

}
