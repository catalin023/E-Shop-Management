package model;

public class ShopProduct extends Product{
    private int priceSell;

    public ShopProduct(int productId, String nume, String category, int priceBuy, int priceSell) {
        super(productId, nume, category, priceBuy);
        this.priceSell = priceSell;
    }

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    @Override
    public String toString() {
        return "ShopProduct{" +
                "productId=" + this.getProductId() +
                ", nume='" + this.getNume() + '\'' +
                ", category='" + this.getCategory() + '\'' +
                ", priceBuy=" + this.getPriceBuy() +
                ", priceSell=" + priceSell +
                '}';
    }
}
