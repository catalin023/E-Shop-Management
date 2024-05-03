package dao;

import model.Shop;

import java.sql.SQLException;

public interface ShopDAO {

    public void updateShop(Shop shop) throws SQLException;
    public float getShop() throws SQLException;
}
