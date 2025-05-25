package com.productServices.productService.Controller;

import com.productServices.productService.Service.productService;
import com.productServices.productService.dto.FakeStoreDTO;
import com.productServices.productService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {

    @Autowired
    private productService productservice;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedprod= productservice.saveProduct(product);
        return ResponseEntity.ok(savedprod);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> prodcuts=productservice.getAllProducts();
        return ResponseEntity.ok(prodcuts);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        Product getIDProduct= productservice.getById(id);
        return ResponseEntity.ok(getIDProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id){
        boolean response= productservice.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateById(@PathVariable("id") int id, @RequestBody Product product){
        Product updateRes=productservice.updateProduct(product,id);
        return ResponseEntity.ok(updateRes);
    }










    @GetMapping("/products/fake")
    public FakeStoreDTO[] getAllproducts(){
        return productservice.getAllProductService();
    }

    @GetMapping("/products/{id}/fake")
    public FakeStoreDTO getProductID(@PathVariable("id") int id){
        if(id<=0){
            throw new IllegalArgumentException("Product doesn't exit");
        }else{
            return productservice.getProductId(id);
        }
    }

    @PostMapping("/products/fake")
    public FakeStoreDTO createProduct(@RequestBody FakeStoreDTO input){
        return productservice.createProduct(input);
    }

    @PutMapping("/products/{id}/fake")
    public FakeStoreDTO updateProduct(@PathVariable("id") int id, @RequestBody FakeStoreDTO input){
        return productservice.updateProductService(id, input);
    }

    @DeleteMapping("/products/{id}/fake")
    public String deleteProduct(@PathVariable("id") int id){
        return productservice.deleteProductService(id);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
