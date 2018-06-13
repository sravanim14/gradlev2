package com.spring.restapi;

import com.spring.restapi.models.Product;
import com.spring.restapi.repositories.ProductRepository;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {
	
    private ProductRepository productRepository;
    static String id_value;
    static String testid_value;
    public Product savedProduct;
	
    @Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Before
    public void init() {
	productRepository.deleteAll();
        Product product = new Product("Dummy Product 1", "The First Product in The world part 1", 100.0, "https://dummyimage.com/600x400/000/aaa");
	Product savedProduct = productRepository.save(product);
	id_value=savedProduct.getId();
    }
		
    @Test
    public void createProduct() {
	Product product = new Product("Dummy Product 2", "The First Product in The world part 2", 200.0, "https://dummyimage.com/600x400/000/bbb");
	Product savedProduct = productRepository.save(product);
        
	testid_value=savedProduct.getId();
	productRepository.delete(savedProduct);
    }

    @Test
    public void findAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        assertNotNull(products);
    }

    @Test
    public void findProductById() {
        Product product = productRepository.findOne(id_value);
        assertNotNull(product);
    }

    @Test
    public void deleteProductWithId() {
        Product product = productRepository.findOne(id_value);
        productRepository.delete(product);
        assertNotNull(product);
    }
	
}
