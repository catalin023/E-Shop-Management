package service;

import dao.DistributorDAO;
import dao.ProductDAO;
import daoImpl.DistributorDAOImpl;
import model.Distributor;
import model.Product;
import service.ProductService;

import java.io.*;
import java.util.ArrayList;
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
            return distributor;
        } else {
            System.out.println("Distributor not found.");
            return null;
        }
    }

    public void readDistributors() {
        List<Distributor> distributors = distributorDAO.getAllDistributors();
        System.out.println("List of Distributors:");
        for (int i = 0; i < distributors.size(); i++) {
            System.out.println((i + 1) + ". " + distributors.get(i).getName());
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
        distributorDAO.deleteDistributor(distributor.getDistributorId());
        System.out.println("Distributor deleted successfully.");
    }

    public void addProductToDistributor(Distributor distributor, Product product) {
        distributorDAO.addProductToDistributor(distributor, product);
        System.out.println("Product added to distributor.");
    }

    public int getDistributorId(Scanner scanner) {
        readDistributors();
        System.out.println("Enter the number of the distributor:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Distributor> distributors = distributorDAO.getAllDistributors();
        if (choice < 1 || choice > distributors.size()) {
            System.out.println("Invalid choice.");
            return -1;
        }
        return distributors.get(choice - 1).getDistributorId();

    }


}
