package daoImpl;

import dao.DistributorDAO;
import model.Distributor;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DistributorDAOImpl implements DistributorDAO {
    List<Distributor> distributors = new ArrayList<>();
    @Override
    public List<Distributor> getAllDistributors() {
        return distributors;
    }

    @Override
    public Distributor getDistributorByName(String name) {
        for (Distributor distributor : distributors){
            if (distributor.getName().equals(name)){
                return distributor;
            }
        }
        return null;
    }

    @Override
    public void addDistributor(Distributor distributor) {
        distributors.add(distributor);
    }

    @Override
    public void updateDistributor(Distributor newDistributor) {
        for (Distributor distributor : distributors){
            if (distributor.getName().equals(newDistributor.getName())){
                distributor.setProducts(newDistributor.getProducts());
                return;
            }
        }
    }

    @Override
    public void deleteDistributor(String name) {
        Iterator<Distributor> iterator = distributors.iterator();
        while (iterator.hasNext()) {
            Distributor distributor = iterator.next();
            if (distributor.getName().equals(name)) {
                iterator.remove();
                return;
            }
        }
    }

    public void addProductToDistributor(String distributorName, Product product) {
        for (Distributor distributor : distributors) {
            if (distributor.getName().equals(distributorName)) {
                distributor.getProducts().add(product);
                return;
            }
        }
    }

}
