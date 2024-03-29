package model;

public class User extends Person{
    private int userId;

    public User(String name, String email, String password, int userId) {
        super(name, email, password);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                "n, ame='" + this.getName() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", balance=" + this.getBalance() + '\'' +
                '}';
    }
}
