package service;

import dao.ProductDAO;
import dao.ShopProductDAO;
import daoImpl.ShopProductDAOImpl;
import model.Product;
import model.Shop;
import model.ShopProduct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ShopProductService {
    private ShopProductDAO shopProductDAO;

    public ShopProductService() {
        this.shopProductDAO = new ShopProductDAOImpl();
    }

    public void createProduct(Product product, int quantity, Scanner scanner) {
        System.out.println("Enter product price to sell: ");
        int priceSell = scanner.nextInt();
        scanner.nextLine();
        ShopProduct shopProduct = new ShopProduct(product, priceSell, quantity);
        shopProductDAO.addProduct(shopProduct);
        Shop.getInstance().setBalance(Shop.getInstance().getBalance() - product.getPriceBuy() * quantity);
    }

    public void readProducts() {
        System.out.println("List of Products:");
        List<ShopProduct> shopProducts = shopProductDAO.getAllProducts();
        for (int i = 0; i < shopProducts.size(); i++) {
            System.out.println((i + 1) + ". " + shopProducts.get(i).toString());
        }
    }

    public void deleteProduct(Scanner scanner) {
        readProducts();
        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<ShopProduct> shopProducts = shopProductDAO.getAllProducts();
        if (choice < 1 || choice > shopProducts.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        shopProductDAO.deleteProduct(shopProducts.get(choice - 1).getProductId());
    }

    public ShopProduct getProduct(Scanner scanner) {
        readProducts();
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<ShopProduct> shopProducts = shopProductDAO.getAllProducts();
        if (choice < 1 || choice > shopProducts.size()) {
            System.out.println("Invalid choice.");
            return null;
        }
        return shopProducts.get(choice - 1);
    }

    public void updatePrice(Scanner scanner) {

        ShopProduct shopProductToUpdate = getProduct(scanner);

        System.out.print("New price to sell: ");
        int newPriceSell = scanner.nextInt();
        scanner.nextLine();
        shopProductToUpdate.setPriceSell(newPriceSell);

        shopProductDAO.updateProduct(shopProductToUpdate);
    }

    public List<ShopProduct> filterProductsByCategory(String category) {
        List<ShopProduct> filteredProducts = new ArrayList<>();
        for (ShopProduct product : shopProductDAO.getAllProducts()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void sortProductsByPrice() {
        shopProductDAO.getAllProducts().sort(Comparator.comparingInt(ShopProduct::getPriceSell));
    }

    public void restockProduct(Product product, Scanner scanner) {
        ShopProduct shopProduct = shopProductDAO.getProductById(product.getProductId());
        System.out.println("Enter quantity to restock: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (shopProduct == null) {
            createProduct(product, quantity, scanner);
        }
        else {
            shopProduct.setQuantity(shopProduct.getQuantity() + quantity);
            shopProductDAO.updateProduct(shopProduct);
            Shop.getInstance().setBalance(Shop.getInstance().getBalance() - product.getPriceBuy() * quantity);
        }
    }

    public List<ShopProduct> viewProducts(Scanner scanner){
        System.out.println("View all/phone/TV/laptop/price");
        String command = scanner.nextLine();
        List<ShopProduct> products;
        switch (command){
            case "all":
                products = shopProductDAO.getAllProducts();
                break;
            case "price":
                products = shopProductDAO.getAllProducts();
                products.sort(Comparator.comparingInt(ShopProduct::getPriceSell));
                break;
            default:
                products = filterProductsByCategory(command);
                break;
        }
        return products;
    }

    public ShopProduct chooseItem(Scanner scanner) {
        List<ShopProduct> products = viewProducts(scanner);
        System.out.println("List of Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
        System.out.println("Enter the number of the product to buy:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return null;
        }

        return products.get(choice - 1);
    }


    public void deductQuantity(ShopProduct productToBuy, int quantity) {
        productToBuy.setQuantity(productToBuy.getQuantity() - quantity);
        shopProductDAO.updateProduct(productToBuy);
    }
}
