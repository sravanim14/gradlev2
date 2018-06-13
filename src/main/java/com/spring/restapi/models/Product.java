/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.restapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author Sravani
 */

@Document(collection = "products")
@ApiModel(description = "Class representing a Products tracked by the Application.")
public class Product {
    @Id
    @ApiModelProperty(notes = "The database generated product ID")
    String id;
	
	@ApiModelProperty(notes = "The Product Name")
    String prodName;
	
	@ApiModelProperty(notes = "The product description")
    String prodDesc;
	
	@ApiModelProperty(notes = "The price of the product", required = true)
    Double prodPrice;
	
	@ApiModelProperty(notes = "The image URL of the product")
    String prodImage;

    public Product() {
    }

    public Product(String prodName, String prodDesc, Double prodPrice, String prodImage) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodImage = prodImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }
}
