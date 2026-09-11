package com.clinic.models;

import java.util.ArrayList;
import java.util.List;

public class Medicine {

    private String name;
    private double price;

    public Medicine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                "\nPrice: " + price;
    }

    public static List<Medicine> createDefaultPharmacyStock(){
        String[] names = {"Paracetamol", "Cough Syrup", "Vitamin C Tablets", "Amoxicillin", "ORS Packet", "Antacid Tablets"};
        double[] prices = {20, 60, 35, 45, 15, 25};

        List<Medicine> stock = new ArrayList<>();
        for (int i=0; i< names.length;i++){
            stock.add(new Medicine(names[i],prices[i]));
        }
        return stock;
    }
}
