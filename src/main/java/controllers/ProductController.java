package controllers;

import models.Product;
import models.User;
import services.ProductService;
import services.UserService;

import java.util.Scanner;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * this a method that receives a product name from the user an invokes the product service to save a new product
     */
    public void saveProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the name of the product you want to save");
        String name = scanner.nextLine();
        System.out.println("please enter the ID of the user");
        int id = scanner.nextInt();
        Product product = new Product();
        product.setName(name);
        product.setCreatedBy(id);
        productService.saveProduct(product);
    }
}
