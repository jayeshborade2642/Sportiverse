package com.sportyverse.service.impl;

import com.sportyverse.dto.ProductDto;
import com.sportyverse.entity.Category;
import com.sportyverse.entity.Description;
import com.sportyverse.entity.Product;
import com.sportyverse.exception.ResourceNotFoundException;
import com.sportyverse.mapper.ProductMapper;
import com.sportyverse.repository.CategoryRepository;
import com.sportyverse.repository.DescriptionRepository;
import com.sportyverse.repository.ProductRepository;
import com.sportyverse.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

//    @Autowired
//    private ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);

        Category category = productDto.getCategoryId().getCategoryId() != null ?
                categoryRepository.findById(productDto.getCategoryId().getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDto.getCategoryId().getCategoryId()))
                :
                categoryRepository.save(productDto.getCategoryId());

        Description description = productDto.getDescriptionId().getDescriptionId() != null ?
                descriptionRepository.findById(productDto.getDescriptionId().getDescriptionId())
                        .orElseThrow(() -> new ResourceNotFoundException("Description not found with id: " + productDto.getDescriptionId().getDescriptionId()))
                :
                descriptionRepository.save(productDto.getDescriptionId());

        product.setCategoryId(category);
        product.setDescriptionId(description);

        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product does not exist with given Id: " + productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductByName(String productName) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product does not exist with given name: " + productName));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setProductPrize(productDto.getProductPrize());
        existingProduct.setDiscountPercentage(productDto.getDiscountPercentage());
        existingProduct.setQuantityInStock(productDto.getQuantityInStock());
        existingProduct.setProductImage(productDto.getProductImage());

        // Retrieve the existing category entity
        Category existingCategory = existingProduct.getCategoryId();
        Long newCategoryId = productDto.getCategoryId().getCategoryId();

        // Check if category needs to be updated
        if (newCategoryId != null && !newCategoryId.equals(existingCategory.getCategoryId())) {
            Category updatedCategory = categoryRepository.findById(newCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + newCategoryId));
            existingProduct.setCategoryId(updatedCategory);
        }

        // Retrieve the existing description entity
        Description existingDescription = existingProduct.getDescriptionId();
        Long newDescriptionId = productDto.getDescriptionId().getDescriptionId();

        // Check if description needs to be updated
        if (newDescriptionId != null && !newDescriptionId.equals(existingDescription.getDescriptionId())) {
            Description updatedDescription = descriptionRepository.findById(newDescriptionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Description not found with id: " + newDescriptionId));
            existingProduct.setDescriptionId(updatedDescription);
        }

        // Save the updated product
        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.mapToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product does not exists with the given Id: " + productId)
        );
        productRepository.deleteById(productId);
    }
}