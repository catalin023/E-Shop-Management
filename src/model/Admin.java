package model;

public class Admin  extends Person{
    private int adminId;

    public Admin(String name, String email, String password, int adminId) {
        super(name, email, password);
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + this.getName() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
