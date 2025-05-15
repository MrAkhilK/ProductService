package com.productServices.productService.Service;

import com.productServices.productService.client.fakeStoreClient;
import com.productServices.productService.dto.FakeStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productService {

    @Autowired
    private fakeStoreClient fakestoreclient;

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
