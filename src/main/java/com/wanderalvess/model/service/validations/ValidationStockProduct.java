package com.wanderalvess.model.service.validations;

import com.wanderalvess.model.entity.Product;
import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.Sales;
import com.wanderalvess.model.service.sales.registration.ProductRegistration;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationStockProduct extends Sales {

    public static void verifyStockProducts(Product product, Scanner scanner) {
        System.out.println("Verificando se possui produtos em estoque para venda...");
        try {
            if (product.getCode() == null) {
                ProductRegistration.registerProduct(product, scanner);
                System.out.println();
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

}
