package com.productServices.productService.Service;

import com.productServices.productService.Repository.ProductRepository;
import com.productServices.productService.client.fakeStoreClient;
import com.productServices.productService.dto.FakeStoreDTO;
import com.productServices.productService.exception.ProductNotFoundException;
import com.productServices.productService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private fakeStoreClient fakestoreclient;
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        Product savedProduct= productRepository.save(product);
        return savedProduct;
    }

    public Product getById(int productid){
        Optional<Product> productOptional=productRepository.findById(productid);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with ID: " +productid+ " Not found");
        }else{
            return productOptional.get();
        }

    }

    public boolean deleteById(int productid){
        productRepository.deleteById(productid);
        return true;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(Product newProduct, int prodId){
        Product savedProduct=getById(prodId);
        newProduct.setId(prodId);
        Product updateProd= productRepository.save(newProduct);
        return updateProd;
    }


    public FakeStoreDTO[] getAllProductService(){
        return fakestoreclient.getAllProducts();
    }

    public FakeStoreDTO getProductId(int id){
        return fakestoreclient.getProductID(id);
    }

    public FakeStoreDTO createProduct(FakeStoreDTO input){
        return  fakestoreclient.postProduct(input);
    }

    public FakeStoreDTO updateProductService(int id, FakeStoreDTO input){
        return fakestoreclient.updateProduct(id, input);
    }

    public String deleteProductService(int id){
        return fakestoreclient.deleteProduct(id);
    }
}
