package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.service.sales.Sales;
import com.wanderalvess.model.service.validations.Validations;

import java.util.Scanner;

import static com.wanderalvess.model.service.sales.registration.GenderRegistration.registerGender;

public class VendorRegistration extends Sales {

    public static void registerVendor(Vendor vendor, Scanner scanner) throws ExceptionUtil {
        System.out.println("Para iniciar a venda de produtos, cadastre os dados do vendedor e do cliente: ");
        System.out.println();
        System.out.println("Iniciando cadastro de novo vendedor... ");
        System.out.println("Digite o nome do novo vendedor: ");
        scanner.skip(".*\n");
        vendor.setName(scanner.nextLine());
        vendor.setAge(Validations.validateAge(scanner, vendor));

        registerGender(scanner, vendor);

        System.out.println("Os dados cadastrados são: ");
        System.out.println(vendor.toString());
        System.out.println();
    }

}
