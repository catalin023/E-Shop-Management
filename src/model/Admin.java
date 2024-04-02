package model;

import java.io.Serializable;

public class Admin  extends Person implements Serializable {
    private static int nextAdmin = 1;
    private int adminId;

    public Admin(String name, String email, String password) {
        super(name, email, password);
        this.adminId = nextAdmin++;
        nextAdmin++;
    }

    public Admin(String name, String email, String password, int adminId) {
        super(name, email, password);
        this.adminId = adminId;
        if(adminId >= nextAdmin){
            nextAdmin = ++adminId;
        }
    }

    public Admin() {
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public static void setNextAdmin(int nextAdmin) {
        Admin.nextAdmin = nextAdmin;
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
