package com.nimko.testrestproject.models;

import com.nimko.testrestproject.dto.ProductDto;
import com.nimko.testrestproject.utils.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate entryDate;
    private int itemCode;
    private String itemName;
    private int itemQuantity;
    private ProductStatus status;

    public ProductDto toDto(){
        return new ProductDto(entryDate,itemCode,itemName,itemQuantity,status.getStatus());
    }
}
