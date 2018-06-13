/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.restapi.controllers;

import com.spring.restapi.models.Product;
import com.spring.restapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author Sravani
 */
@RestController
@RequestMapping("")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ProductController {
    
    //@Autowired
    ProductRepository productRepository;
	
	@Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(method=RequestMethod.GET, value="/products", produces="application/json")
    public Iterable<Product> product() {
        return productRepository.findAll();
    } 
	
 	@ApiOperation(value = "Add a product", response = Product.class)
    @RequestMapping(method=RequestMethod.POST, value="/products", produces="application/json")   
    public String save(@RequestBody Product product) {
        productRepository.save(product);

        return product.getId();
    }
    
	@ApiOperation(value = "Search a product with an ID")
    @RequestMapping(method=RequestMethod.GET, value="/products/{id}", produces="application/json")
    public Product show(@PathVariable String id) {
        return productRepository.findOne(id);
    }
    
    @ApiOperation(value = "Update a product")
    @RequestMapping(method=RequestMethod.PUT, value="/products/{id}", produces="application/json")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        Product prod = productRepository.findOne(id);
        if(product.getProdName() != null)
            prod.setProdName(product.getProdName());
        if(product.getProdDesc() != null)
            prod.setProdDesc(product.getProdDesc());
        if(product.getProdPrice() != null)
            prod.setProdPrice(product.getProdPrice());
        if(product.getProdImage() != null)
            prod.setProdImage(product.getProdImage());
        productRepository.save(prod);
        return prod;
    }
    
	@ApiOperation(value = "Delete a product")
    @RequestMapping(method=RequestMethod.DELETE, value="/products/{id}", produces="application/json")
    public String delete(@PathVariable String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);

        return "product deleted";
    }
}
