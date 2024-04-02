package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Distributor implements Serializable {
    private static int nextDistributorId = 1;
    private int distributorId;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Distributor(String name) {
        this.distributorId = nextDistributorId++;
        this.name = name;
    }

    public Distributor(String name, List<Product> products) {
        this.distributorId = nextDistributorId++;
        this.name = name;
        this.products = products;
    }

    public Distributor(int distributorId, String name, List<Product> products) {
        this.distributorId = distributorId;
        this.name = name;
        this.products = products;
        if(distributorId >= nextDistributorId){
            nextDistributorId = distributorId + 1;
        }
    }

    public int getDistributorId() {
        return distributorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "distributorId=" + distributorId +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
