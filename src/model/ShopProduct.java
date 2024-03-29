package model;

public class ShopProduct extends Product{
    private int priceSell;
    private int quantity;

    public ShopProduct(int productId, String nume, String category, int priceBuy, int priceSell, int quantity) {
        super(productId, nume, category, priceBuy);
        this.priceSell = priceSell;
        this.quantity = quantity;
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
                "productId=" + this.getProductId() +
                ", nume='" + this.getNume() + '\'' +
                ", category='" + this.getCategory() + '\'' +
                ", priceBuy=" + this.getPriceBuy() +
                ", priceSell=" + priceSell +
                ", quantity=" + quantity +
                '}';
    }
}
