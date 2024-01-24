package com.wanderalvess.model.service.sales;

import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.generation.GenerationReceipts;
import com.wanderalvess.model.service.sales.registration.ClientRegistration;
import com.wanderalvess.model.service.sales.registration.VendorRegistration;
import com.wanderalvess.model.service.validations.ValidationStockProduct;
import com.wanderalvess.model.service.validations.Validations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Sales extends ExceptionUtil {
    private Product product = new Product();
    private Client client = new Client();
    private Vendor vendor = new Vendor();

    Scanner scanner = new Scanner(System.in);

    public void initSales() throws InterruptedException, ExceptionUtil {
        try {
            ValidationStockProduct.verifyStockProducts(product, scanner);

            if (vendor.getName() == null) {
                System.out.println("Para iniciar a venda de produtos, cadastre os dados do vendedor e do cliente: ");
                VendorRegistration.registerVendor(vendor, scanner);
            }

            if (client.getName() == null) {
                ClientRegistration.registerClient(client, scanner);
            }

            processSaleProduct();
            System.out.println();
            System.out.println("Processo de vendas finalizado..");
            System.out.println();

        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorSale());
        }
        Thread.sleep(1500);
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

            Validations.validateQuantityForSale(quantitySale, product);
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

    private void calculateValueSale(BigDecimal quantitySale) {
        SimpleDateFormat format = new SimpleDateFormat(("dd/MM/yyyy HH:mm:ss"));
        String dateFormatted = format.format(new Date());

        System.out.println("Calculando custo da venda...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BigDecimal valueSale = product.getPrice().multiply(quantitySale);
        System.out.println("Preço unitário: " + "R$ " + product.getPrice() + ", quantidade comprada: " + quantitySale);

        GenerationReceipts.printReceipt(quantitySale, valueSale, dateFormatted, vendor, client, product);
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
