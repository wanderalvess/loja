package com.wanderalvess.model.sales;

import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.Gender;

import java.util.Scanner;

public class Sales {

    private Product product;
    private Client client;
    private Vendor vendor;

    public String verifyStockProducts() {


        return "Possui x produtos em estoque";
    }

    public String addStockProducts() {
        return "Produto foi adicionado";

    }

    public static void registerClient() {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        Boolean checkGender = Boolean.FALSE;

        System.out.println("Iniciando cadastro de novo cliente... ");
        System.out.println("Informe o CPF para prosseguir o cadastro: ");
        client.setCpf(scanner.nextLine());
        System.out.println("Digite o nome do novo cliente: ");
        client.setName(scanner.nextLine());
        System.out.println("Digite a idade do cliente: ");
        client.setAge(scanner.nextInt());
        scanner.nextLine();

        String gender = registerGender(scanner, client);

        System.out.println("Os dados cadastrados são: ");
        System.out.println(client.toString());

    }

    private static String registerGender(Scanner scanner, Object o) {
        System.out.println("Digite o Gênero (Masculino ou Feminino): ");
        String gender = scanner.nextLine().toLowerCase();
        validateGender(gender, o, scanner);
        return gender;
    }

    private static void validateGender(String gender, Object o, Scanner scanner) {
        if(o instanceof Client client) {
            if(gender.equalsIgnoreCase("masculino")){
                client.setGender(Gender.MALE);
            } else if(gender.equalsIgnoreCase("feminino")){
                client.setGender(Gender.FEMALE);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                registerGender(scanner, o);
            }
        } else if (o instanceof Vendor vendor){
            if(gender.equalsIgnoreCase("masculino")){
                vendor.setGender(Gender.MALE);
            } else if(gender.equalsIgnoreCase("feminino")){
                vendor.setGender(Gender.FEMALE);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                registerGender(scanner, o);
            }
        }

    }

    public static void registerVendor() {
        Vendor vendor = new Vendor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Iniciando cadastro de novo vendedor... ");
        System.out.println("Digite o nome do novo vendedor: ");
        vendor.setName(scanner.nextLine());
        System.out.println("Digite a idade do vendedor: ");
        vendor.setAge(scanner.nextInt());
        scanner.nextLine();

        String gender = registerGender(scanner, vendor);

        System.out.println("Os dados cadastrados são: ");
        System.out.println(vendor.toString());
    }


    //Getters e Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
