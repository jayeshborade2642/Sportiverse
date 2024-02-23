package com.sportyverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionDto {
    private Long descriptionId;
    private String description;
    private String brand;
    private Integer netQuantity;
    private double prodSize;
    private double prodWeight;

}
