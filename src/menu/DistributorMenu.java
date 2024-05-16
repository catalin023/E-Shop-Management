package menu;

import model.Distributor;
import model.User;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class DistributorMenu {

    public static void displayMenu(Scanner scanner, DistributorService distributorService, ProductService productService) throws SQLException {
        while (true) {
            distributorMenu();
            Distributor distributor;
            String distributorCommand = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + distributorCommand);
            switch (distributorCommand) {
                case "create":
                    distributor = distributorService.addDistributor(scanner);
                    distributorPanel(distributor, scanner, distributorService, productService);
                    break;
                case "enter":
                    distributor = distributorService.enterDistributor(scanner);
                    if (distributor != null) {
                        distributorPanel(distributor, scanner, distributorService, productService);
                    }
                    break;
                case "read":
                    distributorService.readDistributors();
                    break;
                case "quit":
                    System.out.println("Exiting distributor");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void distributorMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new distributor");
        System.out.println("enter - Enter the distributor panel");
        System.out.println("read - Read all distributors");
        System.out.println("quit");
    }



    private static void distributorPanel(Distributor distributor, Scanner scanner, DistributorService distributorService, ProductService productService) throws SQLException {
        while (true) {
            distributorPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    productService.readProducts(distributor.getDistributorId());
                    break;
                case "create":
                    productService.createProduct(distributor.getDistributorId(), scanner);
                    //distributorService.addProductToDistributor(distributor, productService.createProduct(distributor.getDistributorId(), scanner));
                    break;
                case "updatep":
                    productService.updateProduct(distributor.getDistributorId(), scanner);
                    break;
                case "updated":
                    distributorService.updateDistributor(distributor, scanner);
                    break;
                case "deletep":
                    productService.deleteProduct(distributor.getDistributorId(), scanner);
                    break;
                case "deleted":
                    distributorService.deleteDistributor(distributor);
                    return;
                case "quit":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }

    }

    private static void distributorPanelMenu() {
        System.out.println("Available commands:");
        System.out.println("read - Read all products");
        System.out.println("create - Create new product");
        System.out.println("updateP - Update product");
        System.out.println("updateD - Update distributor name");
        System.out.println("deleteP - Delete product");
        System.out.println("deleteD - Delete distributor");
        System.out.println("quit");
    }


}

