package dao;

import java.util.List;
import model.Product;

public interface ProductDAO {
    List<Product> getAllProducts();
    List<Product> getProductsByDistributorId(int distributorId);
    Product getProductById(int productId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
