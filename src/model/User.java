package model;

public class User extends Person{
    private int userId;
    private float balance;
    private WishList wishList;

    public User(String name, String email, String password, int userId) {
        super(name, email, password);
        this.userId = userId;
        this.wishList = new WishList();
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




}
