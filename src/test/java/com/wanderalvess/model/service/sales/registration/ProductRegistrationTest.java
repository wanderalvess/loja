package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductRegistrationTest {
    @Test
    void testRegisterProduct() throws ExceptionUtil {

        List<Product> listProduct = new ArrayList<>();

        String input = "\nCaneta\n3,50\n50\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ProductRegistration.registerProduct(listProduct, new Scanner(System.in));

        Assertions.assertEquals("Caneta", listProduct.get(0).getDescription());
        Assertions.assertEquals(new BigDecimal("3.50"), listProduct.get(0).getPrice());
        Assertions.assertEquals(new BigDecimal(50), listProduct.get(0).getStock());
        Assertions.assertTrue(listProduct.get(0).getCode() >= 1 && listProduct.get(0).getCode() <= 2);
        System.out.println("Teste finalizado com sucesso");
    }

}