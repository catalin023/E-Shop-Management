package dao;

import model.Distributor;

import java.util.List;

public interface DistributorDAO {

    List<Distributor> getAllDistributors();
    Distributor getDistributorByName(String name);
    void addDistributor(Distributor distributor);
    void updateDistributor(Distributor distributor);
    void deleteDistributor(String name);
}
