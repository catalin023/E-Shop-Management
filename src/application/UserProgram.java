package application;

import model.Shop;
import model.User;
import service.UserService;

import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        Shop shop = Shop.getInstance();

        shop.loadDataFromFile("data.txt");
        userService.loadUsersFromFile("user_data.txt");

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
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }



    }

    private static void userPanel(User user, Scanner scanner, UserService userService) {
        while (true) {
            userPanelMenu();
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "read":
                    Shop.getInstance().loadDataFromFile("data.txt");
                    Shop.getInstance().readProducts();
                    break;
                case "buy":
                    Shop.getInstance().loadDataFromFile("data.txt");
                    userService.buyItem(user, scanner);
                    Shop.getInstance().saveDataToFile("data.txt");
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


    private static void userMenu() {
        System.out.println("Available commands:");
        System.out.println("create - Create new user");
        System.out.println("enter - Enter the user panel");
        System.out.println("read - Read all users");
        System.out.println("quit");
    }


}