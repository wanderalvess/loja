package com.wanderalvess.model.service.validations;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.Gender;
import com.wanderalvess.model.service.sales.Sales;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ValidationsTest {

    @Test
    public void testValidateGender() throws ExceptionUtil {
        try {
        Client client = new Client();
        Vendor vendor = new Vendor();

        String input = "Masculino\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Validations.validateGender("Masculino", client, scanner);
        assertEquals(Gender.MALE, client.getGender());

        Validations.validateGender("Feminino", vendor, scanner);
        assertEquals(Gender.FEMALE, vendor.getGender());

        input = "Outro\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Validations.validateGender("Outro", client, scanner);

        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testValidateAge() throws ExceptionUtil {
        try {
            Client client = new Client();
            Vendor vendor = new Vendor();

            String input = "46\n50\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Scanner scanner = new Scanner(System.in);

            Validations.validateAge(scanner, client);
            Validations.validateAge(scanner, vendor);

        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testValidateQuantityForSale() throws ExceptionUtil {
        try {
            // Arrange
            BigDecimal stock = new BigDecimal("20");
            Product product = new Product();
            product.setStock(stock);

            BigDecimal quantitySale = new BigDecimal("15");

            Validations.validateQuantityForSale(quantitySale, product);
        } catch (ExceptionUtil e){
            System.out.println(e.getMessage());
        }
    }
}