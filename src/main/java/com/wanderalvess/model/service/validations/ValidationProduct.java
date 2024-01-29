package com.wanderalvess.model.service.validations;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.entity.Client;
import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.entity.Vendor;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.model.service.sales.Cart;
import com.wanderalvess.model.service.sales.Sales;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.wanderalvess.model.service.sales.registration.ProductRegistration.registerProduct;

public class ValidationProduct extends Sales {

    public static void verifyStockProducts(List<Product> listProducts, Scanner scanner) {
        try {
            if (listProducts.isEmpty()) {
                System.out.println("Verificando se possui produtos em estoque para venda...");
                registerProduct(listProducts, scanner);
                System.out.println();
            } else {
                System.out.println("Produtos já cadastrados, carregando vendas...");
            }
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getStockProduct(Product product, Scanner scanner) throws ExceptionUtil {
        try {
            System.out.println("Informe o estoque do produto: ex.: 5  ");
            BigDecimal stockProduct = scanner.nextBigDecimal();
            product.setStock(stockProduct);
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new ExceptionUtil(CodeErrors.ERROR_PRODUCT_STOCK.getDetail());
        }
    }

    public static void verifyCreatedNewProduct(List<Product> listProducts, Scanner scanner) throws ExceptionUtil {
        System.out.println("Deseja cadastrar um novo produto ? ");
        System.out.println("Digite 1 - Sim ou Digite 2 - não.");
        Integer initCreatedProduct = scanner.nextInt();
        try {
            if (initCreatedProduct.equals(1)) {
                registerProduct(listProducts, scanner);
            } else if (initCreatedProduct.equals(2)) {
                verifyStockProducts(listProducts, scanner);
            } else {
                throw new ExceptionUtil(CodeErrors.ERROR_INITIAL.getDetail());
            }
        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findProductsInStock(List<Product> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            Product product = listProducts.get(i);
            System.out.println(i + 1 + ". " + product.getDescription() + " - Estoque: " + product.getStock());
        }
    }

    public static Product selectProductsInStock(List<Product> listProducts, Scanner scanner) throws IllegalArgumentException {
        try {
            System.out.println("Selecione o número correspondente ao produto desejado: ");
            Integer indexProductSelected = scanner.nextInt();

            if (indexProductSelected < 1 || indexProductSelected > listProducts.size()) {
                throw new IllegalArgumentException("Número de produto selecionado inválido.");
            }
            return listProducts.get(indexProductSelected - 1);
        } catch (IllegalArgumentException i) {
            selectProductsInStock(listProducts, scanner);
        }
        return new Product();
    }

    public static BigDecimal selectQuantityProducts(Scanner scanner, BigDecimal quantitySale, Product selectedProduct) throws ExceptionUtil {
        System.out.println("Informe a quantidade: ");

        System.out.println();
        quantitySale = scanner.nextBigDecimal();
        Validations.validateQuantityForSale(quantitySale, selectedProduct);
        return quantitySale;
    }

    public static void addMoreProductToCart(List<Product> listProducts, Scanner scanner, Vendor vendor, Client client) throws ExceptionUtil {
        System.out.println("Deseja adicionar mais um produto? Digite 1 para sim ou Digite 2 para não ");
        BigDecimal moreProduct = scanner.nextBigDecimal();
        if (moreProduct.compareTo(new BigDecimal(1)) >= 0) {
            addProductToCart(listProducts, scanner);
            Cart.displaySummary(vendor, client);
        } else {
            Cart.displaySummary(vendor, client);
        }
    }

    public static void addProductToCart(List<Product> listProducts, Scanner scanner) throws ExceptionUtil {
        BigDecimal quantitySale = new BigDecimal(0);
        findProductsInStock(listProducts);
        Product selectedProduct = selectProductsInStock(listProducts, scanner);
        BigDecimal selectedQuantity = selectQuantityProducts(scanner, quantitySale, selectedProduct);
        Cart.addItem(selectedProduct, selectedQuantity);
    }
}
