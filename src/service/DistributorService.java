package service;

import daoImpl.DistributorDAOImpl;
import model.Distributor;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class DistributorService {
    private final DistributorDAOImpl distributorDAO;

    public DistributorService() {
        this.distributorDAO = new DistributorDAOImpl();
    }


    public Distributor addDistributor(Scanner scanner) throws SQLException {
        System.out.println("Enter distributor name:");
        String name = scanner.nextLine();
        Distributor distributor = new Distributor(name);
        distributorDAO.create(distributor);
        FileManagement.scriereFisierChar(AUDIT_FILE, "create distributor " + name);
        distributor = distributorDAO.readByName(name);
        return distributor;
    }

    public Distributor enterDistributor(Scanner scanner) throws SQLException {
        System.out.println("Enter the name of the distributor you want to enter:");
        String distributorName = scanner.nextLine();
        Distributor distributor = distributorDAO.readByName(distributorName);
        FileManagement.scriereFisierChar(AUDIT_FILE, "read distributor " + distributorName);
        if (distributor != null) {
            System.out.println("Welcome to the distributor panel for: " + distributorName);
            return distributor;
        } else {
            System.out.println("Distributor not found.");
            return null;
        }
    }

    public void readDistributors() throws SQLException {
        List<Distributor> distributors = distributorDAO.read();
        System.out.println("List of Distributors:");
        FileManagement.scriereFisierChar(AUDIT_FILE, "read distributors");
        for (int i = 0; i < distributors.size(); i++) {
            System.out.println((i + 1) + ". " + distributors.get(i).getName());
        }
    }


    public void updateDistributor(Distributor distributor, Scanner scanner) throws SQLException {
        System.out.println("Enter new distributor name:");
        String newName = scanner.nextLine();
        distributor.setName(newName);
        distributorDAO.update(distributor);
        System.out.println("Distributor updated successfully.");
        FileManagement.scriereFisierChar(AUDIT_FILE, "update distributor " + newName);
    }

    public void deleteDistributor(Distributor distributor) throws SQLException {

        distributorDAO.delete(distributor.getDistributorId());
        System.out.println("Distributor deleted successfully.");
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete distributor " + distributor.getName());
    }


    public int getDistributorId(Scanner scanner) throws SQLException {
        readDistributors();
        System.out.println("Enter the number of the distributor:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Distributor> distributors = distributorDAO.read();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read distributors");
        if (choice < 1 || choice > distributors.size()) {
            System.out.println("Invalid choice.");
            return -1;
        }
        return distributors.get(choice - 1).getDistributorId();

    }


}
