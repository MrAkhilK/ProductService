package com.productServices.productService.client;

import com.productServices.productService.dto.FakeStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Component
public class fakeStoreClient {

    @Autowired
    private RestTemplate restTemplate;

    public FakeStoreDTO[] getAllProducts(){
        String fakeStoreURL="https://fakestoreapi.com/products";
        return restTemplate.getForObject(fakeStoreURL,FakeStoreDTO[].class);
    }

    public FakeStoreDTO getProductID(int id){
        String fakeStoreURL= "https://fakestoreapi.com/products/"+ id;
        return restTemplate.getForObject(fakeStoreURL,FakeStoreDTO.class);
    }
}
