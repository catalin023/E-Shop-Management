package dao;

import model.Shop;

import java.sql.SQLException;

public interface ShopDAO {

    public void update(Shop shop) throws SQLException;
    public float read() throws SQLException;
}
