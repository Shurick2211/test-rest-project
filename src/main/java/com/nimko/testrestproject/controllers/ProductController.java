package com.nimko.testrestproject.controllers;

import com.nimko.testrestproject.dto.TableProductsDto;
import com.nimko.testrestproject.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;


    @PostMapping("/add")
    @Operation(summary = "Add product", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<?> addProducts(@RequestBody TableProductsDto dto) {
        if (dto != null) {
            log.info(dto.toString());
            productService.addProducts(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all products", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
