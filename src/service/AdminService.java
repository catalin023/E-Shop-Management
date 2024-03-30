package service;

import dao.AdminDAO;
import daoImpl.AdminDAOImpl;
import model.Admin;
import model.ShopProduct;
import model.Product;

import java.util.List;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAOImpl();
    }

    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    public Admin getAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    public void addAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }

    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    public void deleteAdmin(int adminId) {
        adminDAO.deleteAdmin(adminId);
    }

    public void restockItem(ShopProduct product, int quantity, Admin admin) {
        admin.restockItem(product, quantity);
    }

    public void setSellPrice(Product product, int priceSell, Admin admin) {
        admin.setSellPrice(product, priceSell);
    }

    public void addProductToShop(ShopProduct product, Admin admin) {
        admin.addProductToShop(product);
    }
}
