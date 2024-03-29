package com.wanderalvess.exceptions;

import com.wanderalvess.model.enums.CodeErrors;

public class ExceptionUtil extends Exception {
    public String getErrorClient() {
        return CodeErrors.ERROR_CLIENT.toString();

    }

    public String getErrorSaleQuantityStock() {
        return CodeErrors.ERROR_SALE_QUANTITY_STOCK.toString();

    }

    public String getErrorGender() {
        return CodeErrors.ERROR_GENDER.toString();

    }

    public ExceptionUtil() {
    }

    public ExceptionUtil(String message) {
        super(message);
    }

    public String getErrorClientWithdrew() {
        return CodeErrors.CUSTOMER_WITHDREW_FROM_SALE.toString();
    }

    public String getErrorProduct() {
        return CodeErrors.ERROR_PRODUCT.toString();
    }

    public String getErrorSale() {
        return CodeErrors.ERROR_SALE.toString();
    }

    public String getErrorVendor() {
        return CodeErrors.ERROR_VENDOR.toString();
    }

}
