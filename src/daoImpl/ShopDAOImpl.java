package daoImpl;

import dao.ShopDAO;
import model.Shop;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopDAOImpl implements ShopDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public float getShop() throws SQLException {
        String query = "SELECT * FROM SHOP";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            rs = statement.executeQuery();
            if (rs.next()) {
                float balance = rs.getInt("balance");
                return balance;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    @Override
    public void updateShop(Shop newShop) throws SQLException {
        String sql = "UPDATE SHOP SET balance=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFloat(1, newShop.getBalance());
            statement.executeUpdate();
        }
    }
}
