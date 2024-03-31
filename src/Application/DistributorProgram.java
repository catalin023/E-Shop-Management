package Application;

import model.Distributor;

import service.DistributorService;


import java.util.Scanner;

public class DistributorProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DistributorService distributorService = new DistributorService();

        distributorService.loadDistributorsFromFile("distributor_data.txt");

        while (true) {
            distributorMenu();
            Distributor distributor;
            String distributorCommand = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + distributorCommand);
            switch (distributorCommand) {
                case "create":
                    distributor = distributorService.addDistributor(scanner);
                    distributorPanel(distributor, scanner, distributorService);
                    break;
                case "enter":
                    distributor = distributorService.enterDistributor(scanner);
                    distributorPanel(distributor, scanner, distributorService);
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


    private static void distributorPanel(Distributor distributor, Scanner scanner, DistributorService distributorService) {
        while (true) {
            distributorPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    distributorService.readProducts(distributor, scanner);
                    break;
                case "create":
                    distributorService.addProductToDistributor(distributor, scanner);
                    distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
                    break;
                case "updatep":
                    distributorService.updateProduct(distributor, scanner);
                    distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
                    break;
                case "updated":
                    distributorService.updateDistributor(distributor, scanner);
                    distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
                    break;
                case "deletep":
                    distributorService.deleteProduct(distributor, scanner);
                    distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
                    break;
                case "deleted":
                    distributorService.deleteDistributor(distributor);
                    distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
                    break;
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

    private static void distributorMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new distributor");
        System.out.println("enter - Enter the distributor panel");
        System.out.println("read - Read all distributors");
        System.out.println("quit");
    }

}