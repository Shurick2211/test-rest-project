package com.nimko.testrestproject.services;

import com.nimko.testrestproject.dto.TableProductsDto;
import com.nimko.testrestproject.models.Product;
import com.nimko.testrestproject.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final static String TABLE_NAME = "products";
    private final ProductRepo repo;



    public void addProducts(TableProductsDto dto){
        if (dto.getTable().equals(TABLE_NAME))
            dto.getRecords().forEach(product -> repo.save(product.toEntity()));
    }

    public TableProductsDto getAllProducts(){
        var dto = new TableProductsDto();
        dto.setTable(TABLE_NAME);
        dto.setRecords(repo.findAll().stream().map(Product::toDto).toList());
        return dto;
    }
}
