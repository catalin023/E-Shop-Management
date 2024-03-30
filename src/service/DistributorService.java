package service;

import dao.DistributorDAO;
import daoImpl.DistributorDAOImpl;
import model.Distributor;
import model.Product;

import java.util.List;

public class DistributorService {
    private DistributorDAO distributorDAO;

    public DistributorService() {
        this.distributorDAO = new DistributorDAOImpl();
    }

    public List<Distributor> getAllDistributors() {
        return distributorDAO.getAllDistributors();
    }

    public Distributor getDistributorByName(String name) {
        return distributorDAO.getDistributorByName(name);
    }

    public void addDistributor(Distributor distributor) {
        distributorDAO.addDistributor(distributor);
    }

    public void updateDistributor(Distributor distributor) {
        distributorDAO.updateDistributor(distributor);
    }

    public void deleteDistributor(String name) {
        distributorDAO.deleteDistributor(name);
    }

    public void addProductToDistributor(String distributorName, Product product) {
        distributorDAO.addProductToDistributor(distributorName, product);
    }
}
