package com.wanderalvess.model.sales;

import com.wanderalvess.model.exception.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.model.enums.Gender;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Sales extends ExceptionUtil {
    private Product product = new Product();
    private Client client = new Client();
    private Vendor vendor = new Vendor();

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public void startSalesOperation() {
        try {
            System.out.println("Olá!");
            System.out.println("Iniciando Sistema de vendas...");
            System.out.println();
            System.out.println("Deseja iniciar a operação de vendas de produtos ?");
            System.out.println();
            verifyInitSales();
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorSale());
        }
    }

    private void verifyInitSales() throws ExceptionUtil {
        System.out.println("Digite 1 - Sim ou Digite 2 - não.");
        Integer initSales = scanner.nextInt();
        try {
            if (initSales.equals(1)) {
                initSales();
            } else if (initSales.equals(2)) {
                System.out.println("Caso deseje iniciar uma venda, reinicie o processo. Obrigado!");
                System.out.println("Programa finalizado.");
            } else {
                throw new ExceptionUtil(CodeErrors.ERROR_INITIAL.getDetail());
            }
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
            verifyInitSales();
        }
    }

    public void initSales() {
        try {
            verifyStockProducts();

            if (vendor == null) {
                System.out.println("Para iniciar a venda de produtos, cadastre os dados do vendedor e do cliente: ");
                registerVendor();
            }

            if (client == null) {
                registerClient();
            }

            processSaleProduct();
            System.out.println();
            System.out.println("Processo de vendas finalizado..");
            System.out.println();
            System.out.println("Deseja iniciar processo de vendas novamente?");
            verifyInitSales();
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorSale());
        }
    }

    public void verifyStockProducts() {
        System.out.println("Verificando se possui produtos em estoque para venda...");
        try {
            if (product.getCode() == null) {
                registerProduct();
                System.out.println();
            }
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    private void processSaleProduct() {
        System.out.println("Iniciando processamento da venda de produtos...");

        BigDecimal quantitySale = new BigDecimal(0);

        try {
            System.out.println();
            System.out.println("Para vender o produto: " + product.getDescription()
                    + ", digite a quantidade a ser comprada: " + "(Estoque: " + product.getStock() + ")");
            System.out.println();
            quantitySale = scanner.nextBigDecimal();

            validateQuantityForSale(quantitySale);
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorSaleQuantityStock());
        } finally {
            BigDecimal newStock = product.getStock().subtract(quantitySale);
            product.setStock(newStock);
            calculateValueSale(quantitySale);

            System.out.println("Nova quantidade do produto em estoque: " + product.getDescription() + " é de " + product.getStock());
            System.out.println();
        }
    }

    private void validateQuantityForSale(BigDecimal quantitySale) throws ExceptionUtil {
        if (quantitySale.compareTo(product.getStock()) < 0) {
            throw new ExceptionUtil(CodeErrors.ERROR_SALE_QUANTITY_STOCK.toString());
        }
    }

    private void calculateValueSale(BigDecimal quantitySale) {
        SimpleDateFormat format = new SimpleDateFormat(("dd/MM/yyyy HH:mm:ss"));
        String dateFormatted = format.format(new Date());

        System.out.println("Calculando custo da venda...");

        BigDecimal valueSale = product.getPrice().multiply(quantitySale);
        System.out.println("Preço unitário: " + "R$ " + product.getPrice() + ", quantidade comprada: "
                + quantitySale);

        System.out.println();
        System.out.println("Finalizando compra...");
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("******************** Recibo da Compra *******************");
        System.out.println();
        System.out.println("- Vendedor:     " + vendor.getName());
        System.out.println("- Comprador:    " + client.getName());
        System.out.println("- Produto:      " + product.getDescription());
        System.out.println("- Quantidade:   " + quantitySale);
        System.out.println("- Valor Unitário: " + "R$ " + product.getPrice());
        System.out.println("- Valor final da compra: " + "R$ " + valueSale);
        System.out.println("- Data e hora da compra: " + dateFormatted);
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println();
    }

    private void registerProduct() throws ExceptionUtil {
        try {
            if (product == null) {
                System.out.println("O sistema não possui produtos cadastrados, por favor adicione um produto abaixo.");
            }

            scanner.nextLine();

            if (product.getDescription() == null) {
                System.out.println("Informe a descrição do produto: ");
                String descriptionProduct = scanner.nextLine();
                product.setDescription(descriptionProduct);
            }

            if (product.getPrice() == null) {
                getPriceProduct();
            }
            if (product.getStock() == null) {
                getStockProduct();
            }
            if (product.getCode() == null) {
                product.setCode(random.nextInt(1) + 1);
            }

            System.out.println("Produto cadastrado:  ");
            System.out.println(product.toString());
            System.out.println();
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        } finally {
            verifyStockProducts();
        }
    }

    private void getStockProduct() throws ExceptionUtil {
        try {
            System.out.println("Informe o estoque do produto: ex.: 5  ");
            BigDecimal stockProduct = scanner.nextBigDecimal();
            product.setStock(stockProduct);
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new ExceptionUtil(CodeErrors.ERROR_PRODUCT_STOCK.getDetail());
        }
    }

    private void getPriceProduct() throws ExceptionUtil {
        try {
            System.out.println("Informe o preço do produto: ex.: R$2,50 ");
            BigDecimal priceProduct = scanner.nextBigDecimal();
            scanner.nextLine();
            product.setPrice(priceProduct);
        } catch (InputMismatchException e) {
            throw new ExceptionUtil(CodeErrors.ERROR_PRODUCT_PRICE.getDetail());
        }
    }

    public void registerClient() {
        try {
            System.out.println("Iniciando cadastro de novo cliente... ");
            System.out.println("Informe o CPF para prosseguir o cadastro: ");
            client.setCpf(scanner.nextLine());

            System.out.println("Digite o nome do novo cliente: ");
            client.setName(scanner.nextLine());
            client.setAge(validateAge(scanner, client));

            registerGender(scanner, client);

            System.out.println("Os dados cadastrados são: ");
            System.out.println(client.toString());
            System.out.println();
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorClient());
        }
    }

    private Integer validateAge(Scanner scanner, Object o) throws ExceptionUtil {
        Integer age;
        if(o instanceof Client client) {
            System.out.println("Digite a idade do cliente: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } else if (o instanceof Vendor vendor){
            System.out.println("Digite a idade do vendedor: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } else {
            throw new ExceptionUtil();
        }
        return age;
    }

    private void registerGender(Scanner scanner, Object o) throws ExceptionUtil {
        try {
            System.out.println("Digite o Gênero (Masculino ou Feminino): ");
            String gender = scanner.nextLine().toLowerCase();
            validateGender(gender, o, scanner);
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorGender());
            registerGender(scanner, o);
        }
    }

    private void validateGender(String gender, Object o, Scanner scanner) throws ExceptionUtil {

        if (o instanceof Client client) {
            if (gender.equalsIgnoreCase("masculino")) {
                client.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("feminino")) {
                client.setGender(Gender.FEMALE);
            } else {
                throw new ExceptionUtil();
            }
            if (client.getGender() == null) {
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
            if (vendor.getGender() == null) {
                registerGender(scanner, o);
            }
        }
    }

    public void registerVendor() throws ExceptionUtil {
        System.out.println();
        System.out.println("Iniciando cadastro de novo vendedor... ");
        System.out.println("Digite o nome do novo vendedor: ");
        vendor.setName(scanner.nextLine());
        vendor.setAge(validateAge(scanner, client));

        registerGender(scanner, vendor);

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
