package com.wanderalvess.model.service.sales;

import com.wanderalvess.model.enums.CodeErrors;
import com.wanderalvess.exceptions.ExceptionUtil;

import java.util.Scanner;

public class StartOperationSales extends Sales {

    public void verifyInitSales(Scanner scanner) throws ExceptionUtil {
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
        } catch (ExceptionUtil | InterruptedException e) {
            System.out.println(e.getMessage());
            verifyInitSales(scanner);
        }
    }

    public void startSalesOperation() {
        try {
            System.out.println("Olá!");
            System.out.println("Iniciando Sistema de vendas...");
            System.out.println();
            System.out.println("Deseja iniciar a operação de vendas de produtos ?");
            System.out.println();
            verifyInitSales(scanner);
        } catch (ExceptionUtil e) {
            System.out.println(e.getErrorSale());
        }
    }

}
