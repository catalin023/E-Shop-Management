package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class WishList implements Serializable {
    private Map<Product, Integer> products;

    public WishList() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "products=" + products +
                '}';
    }
}
