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
    }

    public User(String name, String email, String password, int userId, float balance, WishList wishList) {
        super(name, email, password);
        this.userId = userId;
        this.balance = balance;
        this.wishList = wishList;
        if(userId >= nextUserId){
            nextUserId = ++userId;
        }
    }

    public User(String name, String email, String password) {
        super(name, email, password);
        this.wishList = new WishList();
        this.userId = nextUserId;
        nextUserId++;
    }

    public User() {

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

}
