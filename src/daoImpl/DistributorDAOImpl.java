package daoImpl;

import dao.DistributorDAO;
import model.Distributor;
import model.Product;
import model.User;

import java.io.*;
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
    public Distributor getDistributorById(int distributorId) {
        for (Distributor distributor : distributors){
            if (distributor.getDistributorId() == distributorId){
                return distributor;
            }
        }
        return null;
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
            if (distributor.getDistributorId() == newDistributor.getDistributorId()){
                distributor.setProducts(newDistributor.getProducts());
                return;
            }
        }
    }

    @Override
    public void deleteDistributor(int distributorId) {
        for (Distributor distributor : distributors){
            if (distributor.getDistributorId() == distributorId){
                distributors.remove(distributor);
                return;
            }
        }
    }

    public void addProductToDistributor(Distributor distributor, Product product) {
//        distributor.getProducts().add(product);
        updateDistributor(distributor);
    }

//
//    public void saveDistributorsToFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(distributors);
//            System.out.println("Distributors saved to file: " + filename);
//        } catch (IOException e) {
//            System.err.println("Error saving distributors to file: " + e.getMessage());
//        }
//    }
//
//    public void loadDistributorsFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            distributors.clear();
//            distributors.addAll ((List<Distributor>) ois.readObject());
//            System.out.println("Distributors loaded from file: " + filename);
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading distributors from file: " + e.getMessage());
//        }
//    }


}
