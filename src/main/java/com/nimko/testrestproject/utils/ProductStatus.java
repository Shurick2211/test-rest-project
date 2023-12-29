package com.nimko.testrestproject.utils;

import lombok.Getter;

@Getter
public enum ProductStatus {
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String status;
    ProductStatus(String status) {
        this.status = status;
    }

}
