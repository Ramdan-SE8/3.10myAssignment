package com.example.demo_crm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ArrayList<Product> products = new ArrayList<>();

  public ProductController() {
    products.add(new Product("Apple", 1.5));
    products.add(new Product("Orange", 1.2));
    products.add(new Product("Potato", 1.0));
    products.add(new Product("Grape", 3.0));
  }

  // Create a customer
  @PostMapping("")
  public ResponseEntity<Product> createProduct(@RequestBody Product pp) {
    products.add(pp);
    // return customer;
    return new ResponseEntity<>(pp, HttpStatus.CREATED);
  }

  // Read - get all customers
  @GetMapping("")
  public ResponseEntity<ArrayList<Product>> getAllProducts() {
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  // Read - get one customer
  // localhost:8080/customers/35623130-bade-43d6-9bf4-6ea7189301fb
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable String id) {
    try {
      int index = getProductIndex(id);
      return new ResponseEntity<>(products.get(index), HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product pp) {

    try {
      int index = getProductIndex(id);
      products.set(index, pp);
      Product updatedpp = products.get(index);
      return new ResponseEntity<>(updatedpp, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id) {
    try {
      int index = getProductIndex(id);
      products.remove(index);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Helper
  private int getProductIndex(String id) {
    for (Product pp : products) {
      if (pp.getId().equals(id)) {
        return products.indexOf(pp);
      }
    }
    // return -1;
    throw new ProductNotFoundException(id);
  }
    
}
