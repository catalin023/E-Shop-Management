package service;

import dao.DistributorDAO;
import daoImpl.DistributorDAOImpl;
import model.Distributor;
import model.Product;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DistributorService {
    private DistributorDAO distributorDAO;

    public DistributorService() {
        this.distributorDAO = new DistributorDAOImpl();
    }

    public List<Distributor> getAllDistributors() {
        return distributorDAO.getAllDistributors();
    }

    public Distributor getDistributorByName(String name) {
        return distributorDAO.getDistributorByName(name);
    }

    public Distributor addDistributor(Scanner scanner) {
        System.out.println("Enter distributor name:");
        String name = scanner.nextLine();
        Distributor distributor = new Distributor(name);
        distributorDAO.addDistributor(distributor);
        return distributor;
    }

    public Distributor enterDistributor(Scanner scanner) {
        System.out.println("Enter the name of the distributor you want to enter:");
        String distributorName = scanner.nextLine();
        Distributor distributor = distributorDAO.getDistributorByName(distributorName);
        if (distributor != null) {
            System.out.println("Welcome to the distributor panel for: " + distributorName);
        } else {
            System.out.println("Distributor not found.");
        }
        return distributor;
    }

    public void readDistributors() {
        // Implement logic to read all distributors using Scanner input
        List<Distributor> distributors = distributorDAO.getAllDistributors();
        System.out.println("List of Distributors:");
        for (int i = 0; i < distributors.size(); i++) {
            System.out.println((i + 1) + ". " + distributors.get(i).toString());
        }
    }


    public void updateDistributor(Distributor distributor, Scanner scanner) {
        System.out.println("Enter new distributor name:");
        String newName = scanner.nextLine();
        distributor.setName(newName);
        distributorDAO.updateDistributor(distributor);
        System.out.println("Distributor updated successfully.");
    }

    public void deleteDistributor(Distributor distributor) {
        distributorDAO.deleteDistributor(distributor.getName());
        System.out.println("Distributor deleted successfully.");
    }

    public void addProductToDistributor(Distributor distributor, Scanner scanner) {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product category:");
        String productCategory = scanner.nextLine();
        System.out.println("Enter product price:");
        int productPrice = scanner.nextInt();
        scanner.nextLine();
        Product product = new Product(productName, productCategory, productPrice);
        distributorDAO.addProductToDistributor(distributor.getName(), product);
        System.out.println("Product added to distributor.");
    }

    public void readProducts(Distributor distributor, Scanner scanner) {
        if (distributor == null) {
            System.out.println("Distributor not found.");
            return;
        }

        List<Product> products = distributor.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products found for this distributor.");
        } else {
            System.out.println("Products for " + distributor.getName() + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void deleteProduct(Distributor distributor, Scanner scanner) {
        List<Product> products = distributor.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products found for this distributor.");
            return;
        }

        System.out.println("Products for " + distributor.getName() + ":");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }

        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Product productToDelete = products.remove(choice - 1);
        System.out.println("Product deleted: " + productToDelete.toString());
    }

    public void updateProduct(Distributor distributor, Scanner scanner) {

        List<Product> products = distributor.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products found for this distributor.");
            return;
        }

        System.out.println("Products for " + distributor.getName() + ":");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }

        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Product productToUpdate = products.get(choice - 1);
        System.out.println("Enter the new details for the product:");

        System.out.print("New name: ");
        String newName = scanner.nextLine();
        productToUpdate.setName(newName);

        System.out.print("New price: ");
        int newPrice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        productToUpdate.setPriceBuy(newPrice);

        // You can add more fields to update here based on your Product class

        System.out.println("Product updated: " + productToUpdate.toString());
    }

    public void saveDistributorsToFile(List<Distributor> distributors, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(distributors);
            System.out.println("Distributors saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving distributors to file: " + e.getMessage());
        }
    }

    public List<Distributor> loadDistributorsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Distributor> loadedDistributors = (List<Distributor>) ois.readObject();
            distributorDAO.getAllDistributors().clear(); // Clear existing distributors
            distributorDAO.getAllDistributors().addAll(loadedDistributors); // Add loaded distributors
            System.out.println("Distributors loaded from file: " + filename);
            determineNextProductId(loadedDistributors);
            return loadedDistributors;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading distributors from file: " + e.getMessage());
            return null;
        }
    }

    private static void determineNextProductId(List<Distributor> distributors) {

        int maxProductId = 0;
        for (Distributor distributor : distributors) {
            List<Product> products = distributor.getProducts();
            for (Product product : products) {
                if (product.getProductId() > maxProductId) {
                    maxProductId = product.getProductId();
                }
            }
        }
        Product.setNextProductId(maxProductId + 1);
    }




}
