package com.nimko.testrestproject.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nimko.testrestproject.models.Product;
import com.nimko.testrestproject.utils.LocalDateDeserializer;
import com.nimko.testrestproject.utils.LocalDateSerializer;
import com.nimko.testrestproject.utils.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate entryDate;
    private int itemCode;
    private String itemName;
    private int itemQuantity;
    private String status;

    public Product toEntity() {
        var entity = new Product();
        entity.setEntryDate(entryDate);
        entity.setItemCode(itemCode);
        entity.setItemName(itemName);
        entity.setItemQuantity(itemQuantity);
        entity.setStatus(ProductStatus.valueOf(status.toUpperCase()));
        return entity;
    }
}

