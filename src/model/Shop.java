package model;

import java.io.*;
import java.util.*;

public class Shop implements Serializable{
    private static Shop instance;
    private float balance;

    private Shop() {
        balance = 10000;
    }
    public static Shop getInstance(){
        if (instance == null){
            instance = new Shop();
        }
        return instance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "balance= " + balance +
                '}';
    }
}
