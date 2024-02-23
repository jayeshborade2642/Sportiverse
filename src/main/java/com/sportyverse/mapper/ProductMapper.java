package com.sportyverse.mapper;

import com.sportyverse.dto.ProductDto;
import com.sportyverse.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
            product.getProductId(),
            product.getCategoryId(),
            product.getProductName(),
            product.getDescriptionId(),
            product.getProductPrize(),
            product.getDiscountPercentage(),
            product.getQuantityInStock(),
            product.getProductImage()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getCategoryId(),
                productDto.getProductName(),
                productDto.getDescriptionId(),
                productDto.getProductPrize(),
                productDto.getDiscountPercentage(),
                productDto.getQuantityInStock(),
                productDto.getProductImage()
        );
    }
}
