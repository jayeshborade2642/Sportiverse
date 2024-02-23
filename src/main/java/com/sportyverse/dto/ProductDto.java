package com.sportyverse.dto;

import com.sportyverse.entity.Category;
import com.sportyverse.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private Category categoryId;
    private String productName;
    private Description descriptionId;
    private double productPrize;
    private double discountPercentage;
    private Integer quantityInStock;
    private String productImage;

}
