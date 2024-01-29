package com.wanderalvess.model.service.sales.generation;

import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.service.sales.Sales;

import java.math.BigDecimal;

public class GenerationReceipts extends Sales {
    public static void printReceipt(BigDecimal quantitySale, BigDecimal total, String dateFormatted, Vendor vendor, Client client, StringBuilder nameProduct) {
        System.out.println();
        System.out.println("Finalizando compra...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("******************** Recibo da Compra *******************");
        System.out.println();
        System.out.println("- Vendedor:     " + vendor.getName());
        System.out.println("- Comprador:    " + client.getName());
        System.out.println("- Produto:      " + nameProduct);
        System.out.println("- Quantidade:   " + quantitySale);
        System.out.println("- Valor final da compra: " + "R$ " + total);
        System.out.println("- Data e hora da compra: " + dateFormatted);
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println();
    }
}
