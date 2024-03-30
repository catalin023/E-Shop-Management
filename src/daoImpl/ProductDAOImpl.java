package daoImpl;

import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    List<Product> products = new ArrayList<>();
    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : products){
            if (product.getProductId() == productId){
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
        for (Product product : products){
            if (product.getProductId() == newProduct.getProductId()){
                product.setCategory(newProduct.getCategory());
                product.setName(newProduct.getName());
                product.setPriceBuy(newProduct.getPriceBuy());
                return;
            }
        }

    }

    @Override
    public void deleteProduct(int productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == productId) {
                iterator.remove();
                return;
            }
        }
    }
}
