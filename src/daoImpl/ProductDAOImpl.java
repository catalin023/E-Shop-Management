package daoImpl;

import dao.ProductDAO;
import model.Product;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setDistributorId(rs.getInt("distributorId"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPriceBuy(rs.getInt("priceBuy"));
                products.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByDistributorId(int distributorId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT WHERE distributorId=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, distributorId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setDistributorId(rs.getInt("distributorId"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPriceBuy(rs.getInt("priceBuy"));
                products.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM PRODUCT WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            rs = statement.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setDistributorId(rs.getInt("distributorId"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPriceBuy(rs.getInt("priceBuy"));
                return product;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCT (distributorId, name, category, priceBuy) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getDistributorId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getPriceBuy());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateProduct(Product newProduct) throws SQLException {
        String sql = "UPDATE PRODUCT SET distributorId=?, name=?, category=?, priceBuy=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newProduct.getDistributorId());
            statement.setString(2, newProduct.getName());
            statement.setString(3, newProduct.getCategory());
            statement.setInt(4, newProduct.getPriceBuy());
            statement.setInt(5, newProduct.getProductId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM PRODUCT WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }
}
