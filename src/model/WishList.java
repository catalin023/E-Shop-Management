package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class WishList implements Serializable {
    private Map<ShopProduct, Integer> products;

    public WishList() {
        this.products = new HashMap<>();
    }

    public void addProduct(ShopProduct product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public Map<ShopProduct, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "products=" + products +
                '}';
    }
}
