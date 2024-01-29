package com.wanderalvess.model.service.sales;

import com.wanderalvess.model.entity.CartItems;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.service.sales.generation.GenerationReceipts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
    private static List<CartItems> cartItems = new ArrayList<>();;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public static void addItem(Product product, BigDecimal quantity) {

        CartItems item = new CartItems(product, quantity);
        cartItems.add(item);
        System.out.println("Produtos adicionados ao carrinho.");
    }

    public static void displaySummary(Vendor vendor, Client client) {
        System.out.println("\nResumo do Carrinho:");
        SimpleDateFormat format = new SimpleDateFormat(("dd/MM/yyyy HH:mm:ss"));
        String dateFormatted = format.format(new Date());

        BigDecimal quantitySale = new BigDecimal(BigInteger.ZERO);
        StringBuilder nameProduct = new StringBuilder();

        for (int i = 0; i < cartItems.size(); i++) {
            CartItems item = cartItems.get(i);

            quantitySale = quantitySale.add(item.getQuantity());
            nameProduct.append(item.getProduct().getDescription()).append(", ");
            System.out.println((i + 1) + ". " + item.getProduct().getDescription() + " - Quantidade: "
                    + item.getQuantity() + ", Valor unitário: " + item.getProduct().getPrice() );

        }

        BigDecimal total = calculateTotal();
        System.out.println("Total: " + total);
        GenerationReceipts.printReceipt(quantitySale, total, dateFormatted, vendor, client, nameProduct);

    }

    private static BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        try {
            System.out.println("Calculando custo da venda...");

            Thread.sleep(1500);

            for (CartItems item : cartItems) {
                BigDecimal subtotal = item.getProduct().getPrice().multiply(item.getQuantity());
                total = total.add(subtotal);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
