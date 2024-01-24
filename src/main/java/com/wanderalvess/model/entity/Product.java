package com.wanderalvess.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

//Produto
public class Product {
    private Integer code;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public Product() {
    }

    public Product(Integer code, String description, BigDecimal price, BigDecimal stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produto {" +
                " codigo = " + code +
                ", descrição = '" + description + '\'' +
                ", preço = " + price +
                ", estoque = " + stock +
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
