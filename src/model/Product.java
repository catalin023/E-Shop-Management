package model;

import java.io.Serializable;

public class Product implements Serializable {
    private static int nextProductId = 1;
    private int productId;
    private String name;
    private String category;
    private int priceBuy;

    public Product(String nume, String category, int priceBuy) {
        this.productId = nextProductId++;
        this.name = nume;
        this.category = category;
        this.priceBuy = priceBuy;
        nextProductId++;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
                ", nume='" + name + '\'' +
                ", category='" + category + '\'' +
                ", priceBuy=" + priceBuy +
                '}';
    }
}
