package com.nimko.testrestproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableProductsDto {
    private String table;
    private List<ProductDto> records;
}
