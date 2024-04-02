package daoImpl;

import dao.ProductDAO;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();
    private String filename = "dataFiles/product_data.txt";

    public ProductDAOImpl() {
        loadProductsFromFile();
    }

    @Override
    public List<Product> getAllProducts() {
        loadProductsFromFile();
        return products;
    }

    @Override
    public List<Product> getProductsByDistributorId(int distributorId) {
        loadProductsFromFile();
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
        loadProductsFromFile();
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        loadProductsFromFile();
        products.add(product);
        saveProductsToFile();
    }

    @Override
    public void updateProduct(Product newProduct) {
        for (Product product : products) {
            if (product.getProductId() == newProduct.getProductId()) {
                product.setName(newProduct.getName());
                product.setPriceBuy(newProduct.getPriceBuy());
                saveProductsToFile();
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                products.remove(product);
                saveProductsToFile();
                return;
            }
        }
    }


    public void saveProductsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
        } catch (IOException ignored) {
        }
    }

    public void loadProductsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            products = (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

}
