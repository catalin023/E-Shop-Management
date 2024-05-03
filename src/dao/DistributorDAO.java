package dao;

import model.Distributor;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface DistributorDAO {

    List<Distributor> getAllDistributors() throws SQLException;
    Distributor getDistributorById(int distributorId) throws SQLException;
    Distributor getDistributorByName(String name) throws SQLException;
    void addDistributor(Distributor distributor) throws SQLException;
    void updateDistributor(Distributor distributor) throws SQLException;
    void deleteDistributor(int distributorId) throws SQLException;
}
