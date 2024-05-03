package service;

import dao.ShopDAO;
import daoImpl.ShopDAOImpl;
import model.Shop;
import utils.FileManagement;

import java.sql.SQLException;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class ShopService {
    private ShopDAO shopDAO;

    public ShopService() {
        this.shopDAO = new ShopDAOImpl();
    }
    public void updateShop(Shop shop) throws SQLException {
        shopDAO.updateShop(shop);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update shop");
    }

    public float getShop() throws SQLException {
        FileManagement.scriereFisierChar(AUDIT_FILE, "read shop");
        return shopDAO.getShop();
    }
}
