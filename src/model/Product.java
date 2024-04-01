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

    public Product(int productId, String name, String category, int priceBuy) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.priceBuy = priceBuy;
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

    public static void setNextProductId(int nextProductId) {
        Product.nextProductId = nextProductId;
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
