package menu;

import model.Admin;
import model.Product;
import model.User;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {

    public static void displayMenu(Scanner scanner, AdminService adminService, DistributorService distributorService, ProductService productService, ShopProductService shopProductService, ShopService shopService) throws SQLException {
        while (true) {
            adminMenu();
            Admin admin;
            String adminCommand = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + adminCommand);
            switch (adminCommand) {
                case "create":
                    admin = adminService.addAdmin(scanner);
                    adminPanel(admin, scanner, adminService, distributorService, productService, shopProductService, shopService);
                    break;
                case "enter":
                    admin = adminService.enterAdmin(scanner);
                    if (admin != null) {
                        adminPanel(admin, scanner, adminService, distributorService, productService, shopProductService, shopService);
                    }
                    break;
                case "read":
                    adminService.readAdmins();
                    break;
                case "quit":
                    System.out.println("Exiting admin");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void adminMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new admin");
        System.out.println("enter - Enter the admin panel");
        System.out.println("read - Read all admins");
        System.out.println("quit");
    }

    private static void adminPanel(Admin admin, Scanner scanner, AdminService adminService, DistributorService distributorService, ProductService productService, ShopProductService shopProductService, ShopService shopService) throws SQLException {
        while (true) {
            adminPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    System.out.println("Shop balance " + shopService.getShop());
                    shopProductService.readProducts();
                    break;
                case "restock":
                    int distributorId = distributorService.getDistributorId(scanner);
                    if (distributorId == -1) {break;}
                    Product product = productService.getProduct(distributorId, scanner);
                    if (product == null) {break;}
                    shopProductService.restockProduct(product, shopService, scanner);
                    break;
                case "updatep":
                    shopProductService.updatePrice(scanner);
                    break;
                case "updatea":
                    adminService.updateAdmin(admin, scanner);
                    break;
                case "deletep":
                    shopProductService.deleteProduct(scanner);
                    break;
                case "deletea":
                    adminService.deleteAdmin(admin.getAdminId());
                    return;
                case "quit":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void adminPanelMenu() {
        System.out.println("Available commands:");
        System.out.println("read - Read all products");
        System.out.println("restock - Restock product");
        System.out.println("updateP - Update product price");
        System.out.println("updateA - Update admin");
        System.out.println("deleteP - Delete product");
        System.out.println("deleteA - Delete admin");
        System.out.println("quit");
    }




}

