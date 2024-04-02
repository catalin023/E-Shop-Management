package daoImpl;

import dao.ShopDAO;
import model.Product;
import model.Shop;
import model.ShopProduct;

import java.io.*;
import java.util.List;

public class ShopDAOImpl implements ShopDAO {
    private Shop shop;
    private String filename = "dataFiles/shop_data.txt";

    public ShopDAOImpl() {
        loadShopFromFile();
    }

    public Shop getShop() {
        return shop;
    }

    public void updateShop(Shop newShop) {
        shop.setBalance(newShop.getBalance());
        saveShopToFile();
    }


    public void saveShopToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shop);
        } catch (IOException ignored) {
        }
    }

    public void loadShopFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            shop = (Shop) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }
}
