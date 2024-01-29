package com.wanderalvess.model.entity;

import java.math.BigDecimal;

public class CartItems {
    private Product product;
    private BigDecimal quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public CartItems(Product product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
