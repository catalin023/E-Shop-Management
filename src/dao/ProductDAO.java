package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductById(int productId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
