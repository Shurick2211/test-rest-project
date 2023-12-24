package com.nimko.testrestproject.utils;

public enum ProductStatus {
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String status;
    ProductStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
