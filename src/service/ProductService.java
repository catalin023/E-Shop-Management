package service;

import daoImpl.ProductDAOImpl;
import model.Product;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class ProductService {

    private ProductDAOImpl productDAO;

    public ProductService() {
        this.productDAO = new ProductDAOImpl();
    }

    public Product createProduct(int distributorId, Scanner scanner) throws SQLException {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product category:");
        String productCategory = scanner.nextLine();
        System.out.println("Enter product price:");
        int productPrice = scanner.nextInt();
        scanner.nextLine();
        Product product = new Product(distributorId, productName, productCategory, productPrice);
        productDAO.create(product);
        FileManagement.scriereFisierChar(AUDIT_FILE, "create product " + productName);
        return product;
    }

    public void readProducts(int distributorId) throws SQLException {
        System.out.println("List of Products:");
        List<Product> products = productDAO.readByDistributorId(distributorId);
        FileManagement.scriereFisierChar(AUDIT_FILE, "read products");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
    }
    public void deleteProduct(int distributorId, Scanner scanner) throws SQLException {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.readByDistributorId(distributorId);
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        productDAO.delete(products.get(choice - 1).getProductId());
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete product " + products.get(choice - 1).getProductId());
    }

    public void updateProduct(int distributorId, Scanner scanner) throws SQLException {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.readByDistributorId(distributorId);
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
        productDAO.update(productToUpdate);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update product " + productToUpdate.getName());
    }


    public Product getProduct(int distributorId, Scanner scanner) throws SQLException {
        readProducts(distributorId);
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = productDAO.readByDistributorId(distributorId);
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return null;
        }
        FileManagement.scriereFisierChar(AUDIT_FILE, "read product " + products.get(choice - 1).getName());
        return products.get(choice - 1);
    }
}
