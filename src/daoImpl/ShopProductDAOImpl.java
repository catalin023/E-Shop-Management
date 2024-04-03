package daoImpl;


import dao.ShopProductDAO;
import model.Product;
import model.ShopProduct;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShopProductDAOImpl implements ShopProductDAO {


    private List<ShopProduct> products = new ArrayList<>();

    @Override
    public List<ShopProduct> getAllProducts() {
        return products;
    }

    @Override
    public ShopProduct getProductById(int productId) {
        for (ShopProduct product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(ShopProduct product) {
        products.add(product);
    }

    @Override
    public void updateProduct(ShopProduct newProduct) {
        for (ShopProduct product : products) {
            if (product.getProductId() == newProduct.getProductId()) {
                product.setName(newProduct.getName());
                product.setPriceBuy(newProduct.getPriceBuy());
                product.setPriceSell(newProduct.getPriceSell());
                product.setQuantity(newProduct.getQuantity());
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        for (ShopProduct product : products) {
            if (product.getProductId() == productId) {
                products.remove(product);
                return;
            }
        }
    }

//    public void saveProductsToFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(products);
//        } catch (IOException ignored) {
//        }
//    }
//
//    public void loadProductsFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            products = (List<ShopProduct>) ois.readObject();
//        } catch (IOException | ClassNotFoundException ignored) {
//        }
//    }
}
