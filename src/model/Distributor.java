package model;

import java.util.ArrayList;
import java.util.List;

public class Distributor {
    private String name;
    private List<Product> products = new ArrayList<>();

    public Distributor(String name) {
        this.name = name;
    }

    public Distributor(String name, List<Product> products) {
        this.name = name;
        this.products = products;
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

    @Override
    public String toString() {
        return "Distributor{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
