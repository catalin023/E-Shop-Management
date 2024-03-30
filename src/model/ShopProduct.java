package model;

public class ShopProduct extends Product{
    private int priceSell;
    private int quantity;

    public ShopProduct(String nume, String category, int priceBuy, int priceSell, int quantity) {
        super(nume, category, priceBuy);
        this.priceSell = priceSell;
        this.quantity = quantity;
    }

    public ShopProduct(Product product, int priceSell, int quantity) {
        super(product.getName(), product.getCategory(), product.getPriceBuy());
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
                ", nume='" + this.getName() + '\'' +
                ", category='" + this.getCategory() + '\'' +
                ", priceBuy=" + this.getPriceBuy() +
                ", priceSell=" + priceSell +
                ", quantity=" + quantity +
                '}';
    }


}
