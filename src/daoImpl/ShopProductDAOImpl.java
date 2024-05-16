package daoImpl;

import dao.DAOInterface;
import model.ShopProduct;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopProductDAOImpl implements DAOInterface<ShopProduct> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<ShopProduct> read() throws SQLException {
        List<ShopProduct> products = new ArrayList<>();
        String query = "SELECT * FROM SHOP_PRODUCT";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                ShopProduct product = new ShopProduct();
                product.setId(rs.getInt("id"));
                product.setProductId(rs.getInt("productId"));
                product.setDistributorId(rs.getInt("distributorId"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPriceBuy(rs.getInt("priceBuy"));
                product.setPriceSell(rs.getInt("priceSell"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return products;
    }

    public ShopProduct readById(int id) throws SQLException {
        String query = "SELECT * FROM SHOP_PRODUCT WHERE productId=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                ShopProduct product = new ShopProduct();
                product.setId(rs.getInt("id"));
                product.setProductId(rs.getInt("productId"));
                product.setDistributorId(rs.getInt("distributorId"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPriceBuy(rs.getInt("priceBuy"));
                product.setPriceSell(rs.getInt("priceSell"));
                product.setQuantity(rs.getInt("quantity"));
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
    public void create(ShopProduct product) throws SQLException {
        String sql = "INSERT INTO SHOP_PRODUCT VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setInt(2, product.getProductId());
            statement.setInt(3, product.getDistributorId());
            statement.setString(4, product.getName());
            statement.setString(5, product.getCategory());
            statement.setInt(6, product.getPriceBuy());
            statement.setInt(7, product.getPriceSell());
            statement.setInt(8, product.getQuantity());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void update(ShopProduct newProduct) throws SQLException {
        String sql = "UPDATE SHOP_PRODUCT SET priceSell=?, quantity=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newProduct.getPriceSell());
            statement.setInt(2, newProduct.getQuantity());
            statement.setInt(3, newProduct.getId());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM SHOP_PRODUCT WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        }
    }
}
