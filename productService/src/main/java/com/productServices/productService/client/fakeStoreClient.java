package com.productServices.productService.client;

import com.productServices.productService.dto.FakeStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
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

    public FakeStoreDTO postProduct(FakeStoreDTO input){
        String fakeStoreURL="https://fakestoreapi.com/products";
        return restTemplate.postForObject(fakeStoreURL,input,FakeStoreDTO.class);
    }

    public FakeStoreDTO updateProduct(int id, FakeStoreDTO input){
        String fakeStoreURL = "https://fakestoreapi.com/products/"+id;
        return putForObject(fakeStoreURL,input,FakeStoreDTO.class);
    }

    public String deleteProduct(int id){
        String fakeStoreURL= "https://fakestoreapi.com/products/"+id;
        try{
            requestForObject(fakeStoreURL,HttpMethod.DELETE,null, FakeStoreDTO.class);
            return "Product deleted Successfully";
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T putForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private <T> T requestForObject(String url,HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
