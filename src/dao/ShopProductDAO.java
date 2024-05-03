package dao;

import model.ShopProduct;

import java.sql.SQLException;
import java.util.List;

public interface ShopProductDAO {
    List<ShopProduct> getAllProducts() throws SQLException;
    ShopProduct getProductById(int productId) throws SQLException;
    void addProduct(ShopProduct product) throws SQLException;
    void updateProduct(ShopProduct product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
}
