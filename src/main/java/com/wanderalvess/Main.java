package com.wanderalvess;

import com.wanderalvess.exceptions.ExceptionUtil;
import com.wanderalvess.model.service.sales.StartOperationSales;

public class Main {
    public static void main(String[] args) throws ExceptionUtil {
        StartOperationSales startOperationSales = new StartOperationSales();
        startOperationSales.startSalesOperation();
    }
}