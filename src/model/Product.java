package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private int distributorId;
    private String name;
    private String category;
    private int priceBuy;

    public Product(int distributorId, String name, String category, int priceBuy) {
        this.name = name;
        this.category = category;
        this.priceBuy = priceBuy;
        this.distributorId = distributorId;
    }

    public Product(int productId, int distributorId, String name, String category, int priceBuy) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.priceBuy = priceBuy;
        this.distributorId = distributorId;
    }

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", distributorId=" + distributorId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", priceBuy=" + priceBuy +
                '}';
    }
}
