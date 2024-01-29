package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.Sales;
import com.wanderalvess.model.service.validations.ValidationStockProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ProductRegistration extends Sales {
    public static void registerProduct(Product product, Scanner scanner) throws ExceptionUtil {
        Random random = new Random();

        try {
            if (product.equals(new Product())) {
                System.out.println("O sistema não possui produtos cadastrados, por favor adicione um produto abaixo.\n");
                scanner.skip(".*\n");
            }

            if (product.getDescription() == null) {
                System.out.println("\nInforme a descrição do produto: ");
                String descriptionProduct = scanner.nextLine();
                product.setDescription(descriptionProduct);
            }

            if (product.getPrice() == null) {
                getPriceProduct(product, scanner);
            }
            if (product.getStock() == null) {
                ValidationStockProduct.getStockProduct(product, scanner);
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
            ValidationStockProduct.verifyStockProducts(product, scanner);
        }
    }

    private static void getPriceProduct(Product product, Scanner scanner) throws ExceptionUtil {
        try {
            System.out.println("Informe o preço do produto: ex.: 2,50 ");
            Double priceProduct = scanner.nextDouble();
            BigDecimal price = BigDecimal.valueOf(priceProduct);
            product.setPrice(price.setScale(2, RoundingMode.HALF_UP));
        } catch (InputMismatchException e) {
            throw new ExceptionUtil(CodeErrors.ERROR_PRODUCT_PRICE.getDetail());
        }
    }

}
