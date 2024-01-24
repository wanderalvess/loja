package com.wanderalvess.model.entity;

import com.wanderalvess.model.enums.Gender;

import java.math.BigDecimal;
import java.util.Objects;

//Cliente
public class Client {
    private String name;
    private String cpf;
    private BigDecimal age;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        return "Cliente {" +
                " Nome = '" + name + '\'' +
                ", cpf = '" + cpf + '\'' +
                ", idade = " + age +
                ", genêro = " + gender.getDescription() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(name, client.name)) return false;
        if (!Objects.equals(cpf, client.cpf)) return false;
        if (!Objects.equals(age, client.age)) return false;
        return gender == client.gender;
    }

}
