package com.nimko.testrestproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class TableProductsDto {
    private String table;
    private List<ProductDto> records;
}
