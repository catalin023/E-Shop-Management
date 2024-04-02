package dao;

import model.Distributor;
import model.Product;

import java.util.List;

public interface DistributorDAO {

    List<Distributor> getAllDistributors();
    Distributor getDistributorById(int distributorId);
    Distributor getDistributorByName(String name);
    void addDistributor(Distributor distributor);
    void updateDistributor(Distributor distributor);
    void deleteDistributor(int distributorId);
    void addProductToDistributor(Distributor distributorId, Product product);
}
