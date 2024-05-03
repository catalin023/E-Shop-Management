package daoImpl;

import model.ShopProduct;
import model.WishList;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishListDAOImpl {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public WishList getWishList(int userId) throws SQLException {
        WishList wishList = new WishList();
        String query = "SELECT * FROM WISHLIST LEFT JOIN SHOP_PRODUCT ON SHOP_PRODUCT.id = WISHLIST.productId WHERE userId=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
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
                product.setQuantity(rs.getInt("SHOP_PRODUCT.quantity"));
                int quantity = rs.getInt("WISHLIST.quantity");
                wishList.addProduct(product, quantity);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return wishList;
    }


    public void updateWishListItem(int userId, int productId, int quantity) throws SQLException {
        String sql = "UPDATE WISHLIST SET quantity=? WHERE userId=? and productId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, userId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        }
    }

    public void addItem(int userId, int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO WISHLIST VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        }
    }

    public void deleteItems(int userId) throws SQLException {
        String sql = "DELETE FROM WISHLIST WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }
}
