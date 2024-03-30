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
    }

    public Admin() {
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

    public void restockItem(ShopProduct product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity);
    }

    public void setSellPrice(Product product, int priceSell) {
        if (product instanceof ShopProduct) {
            ((ShopProduct) product).setPriceSell(priceSell);
        }
    }

    public void addProductToShop(ShopProduct product) {
        Shop.getInstance().getProducts().add(product);
    }

}
