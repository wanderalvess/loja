package com.wanderalvess.model.service.validations;

import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.model.enums.Gender;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.registration.GenderRegistration;
import com.wanderalvess.model.service.sales.Sales;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations extends Sales {
    public static void validateQuantityForSale(BigDecimal quantitySale, Product product) throws ExceptionUtil {
        if (quantitySale.compareTo(product.getStock()) < 0) {
            throw new ExceptionUtil(CodeErrors.ERROR_SALE_QUANTITY_STOCK.toString());
        }
    }

    public static void validateGender(String gender, Object o, Scanner scanner) throws ExceptionUtil {

        if (o instanceof Client client) {
            if (gender.equalsIgnoreCase("masculino")) {
                client.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("feminino")) {
                client.setGender(Gender.FEMALE);
            } else {
                throw new ExceptionUtil();
            }
            if (client.getGender() == null) {
                GenderRegistration.registerGender(scanner, o);
            }
        } else if (o instanceof Vendor vendor) {
            if (gender.equalsIgnoreCase("masculino")) {
                vendor.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("feminino")) {
                vendor.setGender(Gender.FEMALE);
            } else {
                throw new ExceptionUtil();
            }
            if (vendor.getGender() == null) {
                GenderRegistration.registerGender(scanner, o);
            }
        }
    }

    public static BigDecimal validateAge(Scanner scanner, Object o) throws ExceptionUtil {
        BigDecimal age = new BigDecimal(0);
        try {
            if (o instanceof Client client) {
                System.out.println("Digite a idade do cliente: ");
                age = scanner.nextBigDecimal();
                scanner.nextLine();
            } else if (o instanceof Vendor vendor) {
                System.out.println("Digite a idade do vendedor: ");
                age = scanner.nextBigDecimal();
                scanner.nextLine();
            }
            throw new ExceptionUtil(CodeErrors.ERROR_AGE.getDetail());

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            validateAge(scanner, o);
        }
        return age;
    }


}