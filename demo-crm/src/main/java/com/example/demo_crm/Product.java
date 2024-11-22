package com.example.demo_crm;

import java.util.UUID;

public class Product {
    private String productName;
    private final String id;
    private double productPrice;


    public Product (String productName, double productPrice) {
    this.id = UUID.randomUUID().toString();
    this.productName = productName;
    this.productPrice = productPrice;
  }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public double getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public String getId() {
        return id;
    }

  
    
}
