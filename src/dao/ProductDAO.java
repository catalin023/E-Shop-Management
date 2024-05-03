package dao;

import java.sql.SQLException;
import java.util.List;
import model.Product;

public interface ProductDAO {
    List<Product> getAllProducts() throws SQLException;
    List<Product> getProductsByDistributorId(int distributorId) throws SQLException;
    Product getProductById(int productId) throws SQLException;
    void addProduct(Product product) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
}
