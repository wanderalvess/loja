package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.model.entity.Client;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.Sales;

import java.util.Scanner;

import static com.wanderalvess.model.service.validations.Validations.validateAge;

public class ClientRegistration extends Sales {
    public static void registerClient(Client client, Scanner scanner) {
        try {
            System.out.println("Iniciando cadastro de novo cliente... ");
            System.out.println("Informe o CPF para prosseguir o cadastro: ");
            client.setCpf(scanner.nextLine());

            System.out.println("Digite o nome do novo cliente: ");
            client.setName(scanner.nextLine());
            client.setAge(validateAge(scanner, client));

            //GenderRegistration.registerGender(scanner, client);

            System.out.println("Os dados cadastrados são: ");
            System.out.println(client.toString());
            System.out.println();
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorClient());
        }
    }
}
