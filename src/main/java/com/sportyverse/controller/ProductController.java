package com.sportyverse.controller;

import com.sportyverse.dto.ProductDto;
import com.sportyverse.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    //Add Product RestAPI
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    //Get Product RestAPI
    @GetMapping("/{pid}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("pid") Long productId){
        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }

    //Get All Products RestAPI
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //Get Product by Name RestAPI
    @GetMapping("/byname/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable("name") String productName) {
        ProductDto productDto = productService.getProductByName(productName);
        return ResponseEntity.ok(productDto);
    }

    //Update Product RestAPI
    @PutMapping("/{pid}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("pid") Long productId,
                                                    @RequestBody ProductDto updatedProduct){
        ProductDto productDto = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(productDto);
    }

    //Delete Product RestAPI
    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable("pid") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully..!!");
    }


}
