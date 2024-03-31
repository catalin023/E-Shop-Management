package model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User extends Person implements Serializable {
    private static int nextUserId = 1;
    private int userId;
    private float balance = 0;
    private WishList wishList;

    public User(String name, String email, String password, int userId) {
        super(name, email, password);
        this.userId = userId;
        this.wishList = new WishList();
        if(userId >= nextUserId){
            nextUserId = userId++;
        }
    }

    public User(String name, String email, String password, int userId, float balance, WishList wishList) {
        super(name, email, password);
        this.userId = userId;
        this.balance = balance;
        this.wishList = wishList;
        if(userId >= nextUserId){
            nextUserId = userId++;
        }
    }

    public User(String name, String email, String password) {
        super(name, email, password);
        this.wishList = new WishList();
        this.userId = nextUserId;
        nextUserId++;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + this.getName() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", balance=" + balance +
                ", wishlist=" + wishList +
                '}';
    }

    public void addBalance(int balance){
        this.balance += balance;
    }

    public void deductBalance(float balance){this.balance -= balance;}

    public void addToWishlist(Product product, int quantity) {
        this.getWishList().addProduct(product, quantity);
        System.out.println("Product added to wishlist.");
    }

    public void viewWishlist() {
        WishList wishlist = this.getWishList();
        Map<Product, Integer> products = wishlist.getProducts();
        if (products.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            System.out.println("Wishlist:");
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("Product: " + product.getName() + ", Quantity: " + quantity);
            }
        }
    }
}
