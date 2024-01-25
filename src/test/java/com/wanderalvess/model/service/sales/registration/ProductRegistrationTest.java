package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Product;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ProductRegistrationTest {
    @Test
    void testRegisterProduct() throws ExceptionUtil {

        Product product = new Product();

        String input = "Caneta\n3,50\n50\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ProductRegistration.registerProduct(product, new Scanner(System.in));

        assertEquals("Caneta", product.getDescription());
        assertEquals(new BigDecimal("3.50"), product.getPrice());
        assertEquals(new BigDecimal(50), product.getStock());
        assertTrue(product.getCode() >= 1 && product.getCode() <= 2);
    }

}