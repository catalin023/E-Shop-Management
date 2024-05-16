package service;

import daoImpl.ShopProductDAOImpl;
import model.Product;
import model.Shop;
import model.ShopProduct;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static utils.DatabaseLoginData.AUDIT_FILE;

public class ShopProductService {
    private ShopProductDAOImpl shopProductDAO;

    public ShopProductService() {
        this.shopProductDAO = new ShopProductDAOImpl();
    }

    public void createProduct(Product product, int quantity, ShopService shopService,Scanner scanner) throws SQLException {
        System.out.println("Enter product price to sell: ");
        int priceSell = scanner.nextInt();
        scanner.nextLine();
        ShopProduct shopProduct = new ShopProduct(product, priceSell, quantity);
        shopProductDAO.create(shopProduct);
        FileManagement.scriereFisierChar(AUDIT_FILE, "create shop product " + shopProduct.getName());
        Shop.getInstance().setBalance(shopService.getShop() - product.getPriceBuy() * quantity);
        shopService.updateShop(Shop.getInstance());
    }

    public void readProducts() throws SQLException {
        System.out.println("List of Products:");
        List<ShopProduct> shopProducts = shopProductDAO.read();
        FileManagement.scriereFisierChar(AUDIT_FILE, "read products");
        for (int i = 0; i < shopProducts.size(); i++) {
            System.out.println((i + 1) + ". " + shopProducts.get(i).toString());
        }
    }

    public void deleteProduct(Scanner scanner) throws SQLException {
        readProducts();
        System.out.println("Enter the number of the product to delete:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<ShopProduct> shopProducts = shopProductDAO.read();
        if (choice < 1 || choice > shopProducts.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        shopProductDAO.delete(shopProducts.get(choice - 1).getProductId());
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete shop product " + shopProducts.get(choice - 1).getName());
    }

    public ShopProduct getProduct(Scanner scanner) throws SQLException {
        readProducts();
        System.out.println("Enter the number of the product to update:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<ShopProduct> shopProducts = shopProductDAO.read();
        if (choice < 1 || choice > shopProducts.size()) {
            System.out.println("Invalid choice.");
            return null;
        }
        FileManagement.scriereFisierChar(AUDIT_FILE, "read shop product " + shopProducts.get(choice - 1).getName());
        return shopProducts.get(choice - 1);
    }

    public void updatePrice(Scanner scanner) throws SQLException {

        ShopProduct shopProductToUpdate = getProduct(scanner);

        System.out.print("New price to sell: ");
        int newPriceSell = scanner.nextInt();
        scanner.nextLine();
        shopProductToUpdate.setPriceSell(newPriceSell);

        shopProductDAO.update(shopProductToUpdate);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update shop product " + shopProductToUpdate.getName());
    }

    public List<ShopProduct> filterProductsByCategory(String category) throws SQLException {
        List<ShopProduct> filteredProducts = new ArrayList<>();
        for (ShopProduct product : shopProductDAO.read()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    public void restockProduct(Product product, ShopService shopService,Scanner scanner) throws SQLException {
        ShopProduct shopProduct = shopProductDAO.readById(product.getProductId());
        System.out.println("Enter quantity to restock: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (shopProduct == null) {
            createProduct(product, quantity, shopService,scanner);
        }
        else {
            shopProduct.setQuantity(shopProduct.getQuantity() + quantity);
            shopProductDAO.update(shopProduct);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update shop product " + shopProduct.getName());
            Shop.getInstance().setBalance(shopService.getShop() - product.getPriceBuy() * quantity);
            shopService.updateShop(Shop.getInstance());
        }
    }

    public List<ShopProduct> viewProducts(Scanner scanner) throws SQLException {
        System.out.println("View all/phone/TV/laptop/price");
        String command = scanner.nextLine();
        List<ShopProduct> products;
        switch (command){
            case "all":
                products = shopProductDAO.read();
                break;
            case "price":
                products = shopProductDAO.read();
                products.sort(Comparator.comparingInt(ShopProduct::getPriceSell));
                break;
            default:
                products = filterProductsByCategory(command);
                break;
        }
        FileManagement.scriereFisierChar(AUDIT_FILE, "read shop products");
        return products;
    }

    public ShopProduct chooseItem(Scanner scanner) throws SQLException {
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


    public void deductQuantity(ShopProduct productToBuy, int quantity) throws SQLException {
        productToBuy.setQuantity(productToBuy.getQuantity() - quantity);
        shopProductDAO.update(productToBuy);
        FileManagement.scriereFisierChar(AUDIT_FILE, "update shop product " + productToBuy.getName());
    }
}
