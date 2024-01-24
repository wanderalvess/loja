package com.wanderalvess.model.entity;

import java.util.Objects;

//Produto
public class Product {
    private Integer code;
    private String description;
    private Double price;
    private Integer stock;

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

    public Product() {
    }

    public Product(Integer code, String description, Double price, Integer stock) {
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
