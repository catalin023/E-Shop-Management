package model;

import java.io.Serializable;
import java.util.Objects;

public class ShopProduct extends Product implements Serializable {
    private int id;
    private int priceSell;
    private int quantity;

    public ShopProduct(int productId, int distributorId, String nume, String category, int priceBuy, int priceSell, int quantity) {
        super(productId, distributorId, nume, category, priceBuy);
        this.priceSell = priceSell;
        this.quantity = quantity;
    }

    public ShopProduct(Product product, int priceSell, int quantity) {
        super(product.getProductId(), product.getDistributorId(), product.getName(), product.getCategory(), product.getPriceBuy());
        this.priceSell = priceSell;
        this.quantity = quantity;
    }

    public ShopProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShopProduct{" +
                "id=" + id +
                ", productId=" + this.getProductId() +
                ", nume='" + this.getName() + '\'' +
                ", category='" + this.getCategory() + '\'' +
                ", priceBuy=" + this.getPriceBuy() +
                ", priceSell=" + priceSell +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopProduct that = (ShopProduct) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
