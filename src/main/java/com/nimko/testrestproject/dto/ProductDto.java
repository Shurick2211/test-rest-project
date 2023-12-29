package com.nimko.testrestproject.dto;

import com.nimko.testrestproject.models.Product;
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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate entryDate;
    private int itemCode;
    private String itemName;
    private int itemQuantity;
    private String status;

    public Product toEntity(){
        var entity = new Product();
        entity.setEntryDate(entryDate);
        entity.setItemCode(itemCode);
        entity.setItemName(itemName);
        entity.setItemQuantity(itemQuantity);
        entity.setStatus(ProductStatus.valueOf(status));
        return entity;
    }
}
