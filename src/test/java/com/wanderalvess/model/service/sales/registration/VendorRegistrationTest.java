package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.service.validations.Validations;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class VendorRegistrationTest {

    @Test
    public void registerVendor() throws ExceptionUtil {
        Vendor vendor = new Vendor();

        String name = "João do Teste\n";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        Scanner scanner = new Scanner(System.in);

        vendor.setName(scanner.nextLine());
        scanner.close();

        String age = "23\n";
        System.setIn(new ByteArrayInputStream(age.getBytes()));
        scanner = new Scanner(System.in);

        vendor.setAge(Validations.validateAge(scanner, vendor));
        scanner.close();

        String gender = "\nmasculino\n";
        System.setIn(new ByteArrayInputStream(gender.getBytes()));
        scanner = new Scanner(System.in);

        GenderRegistration.registerGender(scanner, vendor);
        System.out.println(vendor.toString());
        System.out.println("Cadastro finalizado");
        System.out.println("Teste finalizado com sucesso");

    }
}