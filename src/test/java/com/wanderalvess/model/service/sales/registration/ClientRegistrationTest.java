package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.service.validations.Validations;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ClientRegistrationTest {
    @Test
    public void registerClient() throws ExceptionUtil {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);


        String cpf = "09109109111";
        System.setIn(new ByteArrayInputStream(cpf.getBytes()));
        scanner = new Scanner(System.in);
        client.setCpf(scanner.nextLine());
        scanner.close();

        String name = "Maria do Teste\n";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        scanner = new Scanner(System.in);

        client.setName(scanner.nextLine());
        scanner.close();

        String age = "43\n";
        System.setIn(new ByteArrayInputStream(age.getBytes()));
        scanner = new Scanner(System.in);

        client.setAge(Validations.validateAge(scanner, client));
        scanner.close();

        String gender = "\nfeminino\n";
        System.setIn(new ByteArrayInputStream(gender.getBytes()));
        scanner = new Scanner(System.in);

        GenderRegistration.registerGender(scanner, client);
        System.out.println(client.toString());
        System.out.println("Cadastro finalizado");

    }

}