package daoImpl;

import dao.ProductDAO;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();


    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Product> getProductsByDistributorId(int distributorId) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getDistributorId() == distributorId) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product newProduct) {
        for (Product product : products) {
            if (product.getProductId() == newProduct.getProductId()) {
                product.setName(newProduct.getName());
                product.setPriceBuy(newProduct.getPriceBuy());
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        for (Product product : products) {
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
//            products = (List<Product>) ois.readObject();
//        } catch (IOException | ClassNotFoundException ignored) {
//        }
//    }

}
