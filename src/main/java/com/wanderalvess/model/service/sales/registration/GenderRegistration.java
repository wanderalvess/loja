package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.Sales;
import com.wanderalvess.model.service.validations.Validations;

import java.util.Scanner;

public class GenderRegistration extends Sales {

    public static void registerGender(Scanner scanner, Object o) throws ExceptionUtil {
        try {
            System.out.println("Digite o Gênero (Masculino ou Feminino): ");
            scanner.skip(".*\n");
            String gender = scanner.nextLine().toLowerCase();
            Validations.validateGender(gender, o, scanner);
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorGender());
            registerGender(scanner, o);
        }
    }
}
