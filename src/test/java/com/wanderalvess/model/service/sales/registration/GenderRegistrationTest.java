package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.Gender;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class GenderRegistrationTest {
    @Test
    public void testRegisterGender() {
        try {
            String input = "\nMasculino\n\nFeminino\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Scanner scanner = new Scanner(System.in);

            Client client = new Client();
            Vendor vendor = new Vendor();

            GenderRegistration.registerGender(scanner, client);
            assertEquals(Gender.MALE, client.getGender());

            GenderRegistration.registerGender(scanner, vendor);
            assertEquals(Gender.FEMALE, vendor.getGender());
            System.out.println("Teste finalizado com sucesso");

        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

}