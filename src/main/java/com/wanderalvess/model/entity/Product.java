package com.wanderalvess.model.entity;

import java.util.Objects;

//Produto
public class Product {
    private String description;
    private Double price;
    private Integer stock;
    private Integer code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(price, product.price)) return false;
        if (!Objects.equals(stock, product.stock)) return false;
        return Objects.equals(code, product.code);
    }


}
