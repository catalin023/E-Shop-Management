//package application;
//
//import model.Admin;
//import model.Shop;
//import service.AdminService;
//import service.DistributorService;
//import java.util.Scanner;
//
//public class AdminProgram {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        AdminService adminService = new AdminService();
//
//        DistributorService distributorService = new DistributorService();
//
//        Shop shop = Shop.getInstance();
//
//        shop.loadDataFromFile("data.txt");
//        distributorService.loadDistributorsFromFile("distributor_data.txt");
//
//        adminService.loadAdminsFromFile("admin_data.txt");
//
//
//        while (true) {
//            adminMenu();
//            Admin admin;
//            String adminCommand = scanner.nextLine().toLowerCase();
//            System.out.println("Command received: " + adminCommand);
//            switch (adminCommand) {
//                case "create":
//                    admin = adminService.addAdmin(scanner);
//                    adminService.saveAdminsToFile(adminService.getAllAdmins(), "admin_data.txt");
//                    adminPanel(admin, scanner, adminService, distributorService);
//                    break;
//                case "enter":
//                    admin = adminService.enterAdmin(scanner);
//                    adminPanel(admin, scanner, adminService, distributorService);
//                    break;
//                case "read":
//                    adminService.loadAdminsFromFile("admin_data.txt");
//                    adminService.readAdmins();
//                    break;
//                case "quit":
//                    System.out.println("Exiting admin");
//                    return;
//                default:
//                    System.out.println("Wrong command");
//            }
//        }
//
//    }
//
//    private static void adminPanel(Admin admin, Scanner scanner, AdminService adminService, DistributorService distributorService) {
//        while (true) {
//            adminPanelMenu();
//            String command = scanner.nextLine().toLowerCase();
//            switch (command) {
//                case "read":
//                    Shop.getInstance().loadDataFromFile("data.txt");
//                    System.out.println("Shop balance " + Shop.getInstance().getBalance());
//                    Shop.getInstance().readProducts();
//                    break;
//                case "restock":
//                    distributorService.loadDistributorsFromFile("distributor_data.txt");
//                    adminService.restockItem(distributorService, scanner);
//                    Shop.getInstance().saveDataToFile("data.txt");
//                    break;
//                case "updatep":
//                    Shop.getInstance().loadDataFromFile("data.txt");
//                    adminService.updateProduct(scanner);
//                    Shop.getInstance().saveDataToFile("data.txt");
//                    break;
//                case "updatea":
//                    adminService.updateAdmin(admin, scanner);
//                    adminService.saveAdminsToFile(adminService.getAllAdmins(), "admin_data.txt");
//                    break;
//                case "deletep":
//                    Shop.getInstance().loadDataFromFile("data.txt");
//                    adminService.deleteProduct(scanner);
//                    Shop.getInstance().saveDataToFile("data.txt");
//                    break;
//                case "deletea":
//                    adminService.deleteAdmin(admin.getAdminId());
//                    adminService.saveAdminsToFile(adminService.getAllAdmins(), "admin_data.txt");
//                    break;
//                case "quit":
//                    System.out.println("Exiting");
//                    return;
//                default:
//                    System.out.println("Wrong command");
//            }
//        }
//    }
//
//    private static void adminPanelMenu() {
//        System.out.println("Available commands:");
//        System.out.println("read - Read all products");
//        System.out.println("restock - Restock product");
//        System.out.println("updateP - Update product price");
//        System.out.println("updateA - Update admin");
//        System.out.println("deleteP - Delete product");
//        System.out.println("deleteA - Delete admin");
//        System.out.println("quit");
//    }
//
//    private static void adminMenu() {
//        System.out.println("Available commands:");
//        System.out.println("create - Create new admin");
//        System.out.println("enter - Enter the admin panel");
//        System.out.println("read - Read all admins");
//        System.out.println("quit");
//    }
//
//
//}