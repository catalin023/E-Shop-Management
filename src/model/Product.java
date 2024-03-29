package model;

public abstract class Product {
    private int productId;
    private String nume;
    private String category;
    private int priceBuy;

    public Product(int productId, String nume, String category, int priceBuy) {
        this.productId = productId;
        this.nume = nume;
        this.category = category;
        this.priceBuy = priceBuy;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
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
                ", nume='" + nume + '\'' +
                ", category='" + category + '\'' +
                ", priceBuy=" + priceBuy +
                '}';
    }
}
