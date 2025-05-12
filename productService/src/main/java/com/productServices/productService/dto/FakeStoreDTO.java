package com.productServices.productService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private ratingDTOresp rating;
}
