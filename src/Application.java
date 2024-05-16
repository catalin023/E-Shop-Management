import menu.AdminMenu;
import menu.DistributorMenu;
import menu.UserMenu;
import model.*;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        AdminService adminService = new AdminService();
        UserService userService = new UserService();
        DistributorService distributorService = new DistributorService();
        ProductService productService = new ProductService();
        ShopProductService shopProductService = new ShopProductService();
        ShopService shopService = new ShopService();

        while (true) {
            menu();

            String command = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + command);
            switch(command){
                case "user":
                    UserMenu.displayMenu(scanner, userService, shopProductService, shopService);
                    break;
                case "admin":
                    AdminMenu.displayMenu(scanner, adminService, distributorService, productService, shopProductService, shopService);
                    break;
                case "distributor":
                    DistributorMenu.displayMenu(scanner, distributorService, productService);
                    break;
                case "quit":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void menu() {
        System.out.println("Choose your role:");
        System.out.println("user");
        System.out.println("admin");
        System.out.println("distributor");
        System.out.println("quit");
        System.out.println("Enter command:");
    }

}