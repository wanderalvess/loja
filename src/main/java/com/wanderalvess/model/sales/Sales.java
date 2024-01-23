package com.wanderalvess.model.sales;

import com.wanderalvess.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.Gender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Sales extends ExceptionUtil{

    private Product product;
    private Client client = new Client();
    private Vendor vendor = new Vendor();

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public void startSalesOperation() throws ExceptionUtil {
        System.out.println("Olá!");
        System.out.println("Iniciando Sistema de vendas...");
        verifyInitSales();
    }

    private void verifyInitSales() throws ExceptionUtil {
        System.out.println("Deseja iniciar a operação de vendas de produtos ?");
        System.out.println("Digite 1 - Sim ou Digite 2 - não.");
        Integer initSales = scanner.nextInt();

        if (initSales.equals(1)) {
            initSales();
        } else if (initSales.equals(2)) {
            System.out.println("Caso deseje iniciar uma venda, reinicie o processo. Obrigado!");
            System.out.println("Programa finalizado.");
        } else {
            System.out.println("O número " + initSales.toString() + ", não corresponde com as opções: 1 para Sim e 2 para não");
            verifyInitSales();
        }
    }

    public void initSales() throws ExceptionUtil {
        verifyStockProducts();
        System.out.println();
        System.out.println("Para iniciar a venda de produtos, cadastre os dados do vendedor e do cliente: ");
        registerVendor();
        registerClient();
        System.out.println("Iniciando processamento da venda de produtos...");
        processSaleProduct();
        System.out.println("Processo de vendas finalizado");
    }

    public void verifyStockProducts() {
        System.out.println("Verificando se possui produtos em estoque para venda...");

        if (product == null) {
            System.out.println("O sistema não possui produtos cadastrados, por favor adicione um produto abaixo.");
            registerProduct();
        }
    }

    private void processSaleProduct() {
        System.out.println();
        System.out.println("Para vender o produto: " + product.getDescription()
                + ", digite a quantidade a ser comprada: " + "(Estoque: " + product.getStock() + ")");
        Integer quantitySale = scanner.nextInt();

        validateQuantityForSale(quantitySale);

        Integer newStock = product.getStock() - quantitySale;
        product.setStock(newStock);
        calculateValueSale(quantitySale);

        System.out.println("Nova quantidade do produto em estoque: " + product.getDescription() + " é de " + product.getStock());
        System.out.println();
    }

    private void validateQuantityForSale(Integer quantitySale) {
        if (quantitySale > product.getStock()) {
            System.out.println("Não possuímos essa quantidade no estoque, por favor digite a quantidade até: " + product.getStock());
            processSaleProduct();
        }
    }

    private void calculateValueSale(Integer quantitySale) {
        SimpleDateFormat format = new SimpleDateFormat(("dd/MM/yyyy HH:mm:ss"));
        String dateFormatted = format.format(new Date());

        System.out.println("Calculando custo da venda...");

        Double valueSale = product.getPrice() * quantitySale;
        System.out.println("Preço unitário: " + "R$ " + product.getPrice() + ", quantidade comprada: "
                + quantitySale);

        System.out.println();
        System.out.println("Finalizando compra...");
        System.out.println();
        System.out.println(" **** Recibo da Compra **** ");
        System.out.println();
        System.out.println("Produto: " + product.getDescription());
        System.out.println("Quantidade: " + quantitySale);
        System.out.println("Valor Unitário: " + "R$ " + product.getPrice());
        System.out.println("Valor final da compra: " + "R$ " + valueSale);
        System.out.println("Data e hora da compra: " + dateFormatted);
        System.out.println("****************************");
        System.out.println();
    }

    private void registerProduct() {
        scanner.nextLine();

        System.out.println("Informe a descrição do produto: ");
        String descriptionProduct = scanner.nextLine();

        System.out.println("Informe o preço do produto: ex.: R$ 2,50 ");
        Double priceProduct = scanner.nextDouble();

        System.out.println("Informe o estoque do produto: ex.: 5  ");
        Integer stockProduct = scanner.nextInt();
        scanner.nextLine();

        product = new Product(0, descriptionProduct, priceProduct, stockProduct);
        product.setCode(random.nextInt());
        System.out.println("Produto cadastrado:  ");
        System.out.println(product.toString());
        System.out.println();
    }

    public void registerClient() {
        try {
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
            System.out.println();
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorClient());

        }

    }

    private String registerGender(Scanner scanner, Object o) throws ExceptionUtil {
        String gender = new String();
        try {
            System.out.println("Digite o Gênero (Masculino ou Feminino): ");
            gender = scanner.nextLine().toLowerCase();
            validateGender(gender, o, scanner);
        } catch (ExceptionUtil e){
            System.out.println(e.getErrorGender());
            registerGender(scanner, o);
        }
        return gender;
    }

    private void validateGender(String gender, Object o, Scanner scanner) throws ExceptionUtil{

        if (o instanceof Client client) {
            if (gender.equalsIgnoreCase("masculino")) {
                client.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("feminino")) {
                client.setGender(Gender.FEMALE);
            } else {
                throw new ExceptionUtil();
            }
            if (client.getGender() == null){
                registerGender(scanner, o);
            }
        } else if (o instanceof Vendor vendor) {
            if (gender.equalsIgnoreCase("masculino")) {
                vendor.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("feminino")) {
                vendor.setGender(Gender.FEMALE);
            } else {
                throw new ExceptionUtil();
            }
            if (vendor.getGender() == null){
                registerGender(scanner, o);
            }
        }

    }

    public void registerVendor() throws ExceptionUtil {
        System.out.println("Iniciando cadastro de novo vendedor... ");
        System.out.println("Digite o nome do novo vendedor: ");
        vendor.setName(scanner.nextLine());

        System.out.println("Digite a idade do vendedor: ");
        vendor.setAge(scanner.nextInt());
        scanner.nextLine();

        String gender = registerGender(scanner, vendor);

        System.out.println("Os dados cadastrados são: ");
        System.out.println(vendor.toString());
        System.out.println();

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
