package service;

import dao.ProductDAO;
import daoImpl.ProductDAOImpl;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAOImpl();
    }

    public Product createProduct(int distributorId, Scanner scanner) {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product category:");
        String productCategory = scanner.nextLine();
        System.out.println("Enter product price:");
        int productPrice = scanner.nextInt();
        scanner.nextLine();
        Product product = new Product(distributorId, productName, productCategory, productPrice);
        productDAO.addProduct(product);
        return product;
    }

    public void readProducts(int distributorId) {
        System.out.println("List of Products:");
        List<Product> products = productDAO.getProductsByDistributorId(distributorId);
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
    }
    public void deleteProduct(int distributorId, Scanner scanner) {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.getProductsByDistributorId(distributorId);
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        productDAO.deleteProduct(products.get(choice - 1).getProductId());
    }

    public void updateProduct(int distributorId, Scanner scanner) {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.getProductsByDistributorId(distributorId);
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        Product productToUpdate = products.get(choice - 1);
        System.out.println("Enter the new name for the product:");
        String newName = scanner.nextLine();
        productToUpdate.setName(newName);
        System.out.println("Enter the new price for the product:");
        int newPrice = scanner.nextInt();
        scanner.nextLine();
        productToUpdate.setPriceBuy(newPrice);
        productDAO.updateProduct(productToUpdate);
    }


    public Product getProduct(int distributorId, Scanner scanner) {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.getProductsByDistributorId(distributorId);
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return null;
        }
        return products.get(choice - 1);
    }
}
