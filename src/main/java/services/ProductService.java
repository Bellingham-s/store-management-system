package services;

import models.Product;
import models.Store;
import repositories.ProductRepository;
import repositories.StoreRepository;

public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * this is a method that saves a product that doesn't exist on the db
     * @param product
     */
    public void saveProduct(Product product){
        if(productRepository.findByName(product.getName()) == null) {
            this.productRepository.SaveProduct(product);
        }
        else{
            System.out.println("product already exists");
        }
    }
}
