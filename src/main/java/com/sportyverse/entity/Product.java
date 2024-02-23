package com.sportyverse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Category categoryId;

    @Column(name = "product_name")
    private String productName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Description descriptionId;

    @Column(name = "product_prize")
    private double productPrize;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "product_quantity")
    private Integer quantityInStock;

    @Column(name = "product_image")
    private String productImage;

}
