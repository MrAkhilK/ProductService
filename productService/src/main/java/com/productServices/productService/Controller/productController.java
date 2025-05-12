package com.productServices.productService.Controller;

import com.productServices.productService.Service.productService;
import com.productServices.productService.dto.FakeStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class productController {

    @Autowired
    private productService productservice;

    @GetMapping("/products")
    public FakeStoreDTO[] getAllproducts(){
        return productservice.getAllProductService();
    }

    @GetMapping("/products/{id}")
    public FakeStoreDTO getProductID(@PathVariable("id") int id){
        return productservice.getProductId(id);
    }

}
