package dao;

import model.ShopProduct;

import java.util.List;

public interface ShopProductDAO {
    List<ShopProduct> getAllProducts();
    ShopProduct getProductById(int productId);
    void addProduct(ShopProduct product);
    void updateProduct(ShopProduct product);
    void deleteProduct(int productId);
}
