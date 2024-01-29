package com.wanderalvess.model.service.sales.registration;

import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.Sales;
import com.wanderalvess.model.service.validations.ValidationProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.wanderalvess.model.service.validations.ValidationProduct.verifyCreatedNewProduct;

public class ProductRegistration extends Sales {
    public static void registerProduct(List<Product> listProducts, Scanner scanner) throws ExceptionUtil {
        Random random = new Random();
        Product product = new Product();

        try {
            if (listProducts.isEmpty()) {
                System.out.println("O sistema não possui produtos cadastrados, por favor adicione um produto abaixo.\n");
                scanner.skip(".*\n");
            } else{
                System.out.println("Produtos já cadastrados: " + listProducts.toString() + "\n");
                scanner.skip(".*\n");
            }

            System.out.println("\n Informe a descrição do produto: ");
            String descriptionProduct = scanner.nextLine();
            product.setDescription(descriptionProduct);

            getPriceProduct(product, scanner);

            ValidationProduct.getStockProduct(product, scanner);

            product.setCode(random.nextInt(1) + 1);

            listProducts.add(product);

            System.out.println("Produto cadastrado:  ");
            System.out.println(product.toString());
            System.out.println();
            verifyCreatedNewProduct(listProducts, scanner);

        } catch (ExceptionUtil e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getPriceProduct(Product product, Scanner scanner) throws ExceptionUtil {
        try {
            System.out.println("Informe o preço do produto: ex.: 2,50 ");
            Double priceProduct = scanner.nextDouble();
            BigDecimal price = BigDecimal.valueOf(priceProduct);
            product.setPrice(price.setScale(2, RoundingMode.HALF_UP));
        } catch (InputMismatchException e) {
            System.out.println("Erro ao preencher preço do produto.");
            getPriceProduct(product, scanner);
        }
    }

}
