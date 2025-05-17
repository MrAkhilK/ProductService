package com.productServices.productService.Controller;

import com.productServices.productService.Service.productService;
import com.productServices.productService.dto.FakeStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(id<=0){
            throw new IllegalArgumentException("Product doesn't exit");
        }else{
            return productservice.getProductId(id);
        }
    }

    @PostMapping("/products")
    public FakeStoreDTO createProduct(@RequestBody FakeStoreDTO input){
        return productservice.createProduct(input);
    }

    @PutMapping("/products/{id}")
    public FakeStoreDTO updateProduct(@PathVariable("id") int id, @RequestBody FakeStoreDTO input){
        return productservice.updateProductService(id, input);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return productservice.deleteProductService(id);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
