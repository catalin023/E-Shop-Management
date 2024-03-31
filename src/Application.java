import model.Admin;
import model.Distributor;
import model.Shop;
import model.User;
import service.AdminService;
import service.DistributorService;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        AdminService adminService = new AdminService();
        UserService userService = new UserService();
        DistributorService distributorService = new DistributorService();

        Shop shop = Shop.getInstance();

        shop.loadDataFromFile("data.txt");
        distributorService.loadDistributorsFromFile("distributor_data.txt");
        userService.loadUsersFromFile("user_data.txt");
        adminService.loadAdminsFromFile("admin_data.txt");

        while (true) {
            menu();

            String command = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + command);
            switch(command){
                case "user":
                    userMenu:
                    while (true) {
                        userMenu();
                        User user;
                        String userCommand = scanner.nextLine().toLowerCase();
                        System.out.println("Command received: " + userCommand);
                        switch (userCommand) {
                            case "create":
                                user = userService.addUser(scanner);
                                userPanel(user, scanner, userService);
                                break;
                            case "enter":
                                user = userService.enterUser(scanner);
                                userPanel(user, scanner, userService);
                                break;
                            case "read":
                                userService.readUsers();
                                break;
                            case "quit":
                                System.out.println("Exiting user");
                                break userMenu;
                            default:
                                System.out.println("Wrong command");
                        }
                    }
                    break;
                case "admin":
                    adminMenu:
                    while (true) {
                        adminMenu();
                        Admin admin;
                        String adminCommand = scanner.nextLine().toLowerCase();
                        System.out.println("Command received: " + adminCommand);
                        switch (adminCommand) {
                            case "create":
                                admin = adminService.addAdmin(scanner);
                                adminPanel(admin, scanner, adminService, distributorService);
                                break;
                            case "enter":
                                admin = adminService.enterAdmin(scanner);
                                adminPanel(admin, scanner, adminService, distributorService);
                                break;
                            case "read":
                                adminService.readAdmins();
                                break;
                            case "quit":
                                System.out.println("Exiting admin");
                                break adminMenu;
                            default:
                                System.out.println("Wrong command");
                        }
                    }
                    break;
                case "distributor":
                    distributorMenu:
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
                                break distributorMenu;
                            default:
                                System.out.println("Wrong command");
                        }
                    }
                    break;
                case "quit":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Wrong command");
            }

            shop.saveDataToFile("data.txt");
            distributorService.saveDistributorsToFile(distributorService.getAllDistributors(), "distributor_data.txt");
            adminService.saveAdminsToFile(adminService.getAllAdmins(), "admin_data.txt");
            userService.saveUsersToFile(userService.getAllUsers(), "user_data.txt");
        }


    }

    private static void userPanel(User user, Scanner scanner, UserService userService) {
        while (true) {
            userPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    Shop.getInstance().readProducts();
                    break;
                case "buy":
                    userService.buyItem(user, scanner);
                    break;
                case "update":
                    userService.updateUser(user, scanner);
                    break;
                case "add":
                    userService.addBalance(user, scanner);
                    break;
                case "delete":
                    userService.deleteUser(user.getUserId());
                    return;
                case "wishlist":
                    userService.readWishlist(user, scanner);
                    break;
                case "quit":
                    System.out.println("Exiting user panel.");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void userPanelMenu() {
        System.out.println("Available commands:");
        System.out.println("read - Read all products");
        System.out.println("buy - Buy product");
        System.out.println("update - Update user's info");
        System.out.println("add - Add balance");
        System.out.println("delete - Delete user");
        System.out.println("wishlist - Read wishlist");
        System.out.println("quit");
    }

    private static void adminPanel(Admin admin, Scanner scanner, AdminService adminService, DistributorService distributorService) {
        while (true) {
            adminPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    Shop.getInstance().readProducts();
                    break;
                case "restock":
                    distributorService.loadDistributorsFromFile("distributor_data.txt");
                    adminService.restockItem(distributorService, scanner);
                    break;
                case "updatep":
                    adminService.updateProduct(scanner);
                    break;
                case "updatea":
                    adminService.updateAdmin(admin, scanner);
                    break;
                case "deletep":
                    adminService.deleteProduct(scanner);
                    break;
                case "deletea":
                    adminService.deleteAdmin(admin.getAdminId());
                    break;
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

    private static void adminMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new admin");
        System.out.println("enter - Enter the admin panel");
        System.out.println("read - Read all admins");
        System.out.println("quit");
    }

    private static void userMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new user");
        System.out.println("enter - Enter the user panel");
        System.out.println("read - Read all users");
        System.out.println("quit");
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