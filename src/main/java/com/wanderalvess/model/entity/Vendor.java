package com.wanderalvess.model.entity;

import com.wanderalvess.model.enums.Gender;

import java.math.BigDecimal;
import java.util.Objects;

//Vendedor
public class Vendor {
    private String name;
    private BigDecimal age;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Vendedor {" +
                " nome = '" + name + '\'' +
                ", idade = " + age +
                ", genêro = " + gender.getDescription() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendor vendor = (Vendor) o;

        if (!Objects.equals(name, vendor.name)) return false;
        if (!Objects.equals(age, vendor.age)) return false;
        return gender == vendor.gender;
    }

}


