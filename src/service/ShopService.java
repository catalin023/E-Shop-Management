package service;

import dao.ShopDAO;
import daoImpl.ShopDAOImpl;
import model.Shop;

import java.sql.SQLException;

public class ShopService {
    private ShopDAO shopDAO;

    public ShopService() {
        this.shopDAO = new ShopDAOImpl();
    }
    public void updateShop(Shop shop) throws SQLException {
        shopDAO.updateShop(shop);
    }

    public float getShop() throws SQLException {
        return shopDAO.getShop();
    }
}
