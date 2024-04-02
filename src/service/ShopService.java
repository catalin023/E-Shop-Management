package service;

import dao.ShopDAO;
import daoImpl.ShopDAOImpl;
import model.Shop;

public class ShopService {
    private ShopDAO shopDAO;

    public ShopService() {
        this.shopDAO = new ShopDAOImpl();
    }
    public void updateShop(Shop shop) {
        shopDAO.updateShop(shop);
    }

    public Shop getShop() {
        return shopDAO.getShop();
    }
}
