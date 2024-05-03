package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Distributor implements Serializable {
    private int distributorId;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Distributor(String name) {
        this.name = name;
    }

    public Distributor(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public Distributor(int distributorId, String name, List<Product> products) {
        this.distributorId = distributorId;
        this.name = name;
        this.products = products;
    }

    public Distributor() {

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
