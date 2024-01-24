package com.wanderalvess.model.enums;

public enum CodeErrors {
    DEFAULT(1,"Ocorreu um erro, entre em contato com o administrador do sistema","Ocorreu um erro no processamento dessa ação"),
    ERROR_VENDOR(2,"Erro ao cadastrar o vendedor, confirme se preencheu todos os campos corretamento","Erro ao cadastrar vendedor"),
    ERROR_CLIENT(3,"Erro ao cadastrar o cliente, confirme se preencheu todos os campos corretamento","Erro ao cadastrar cliente"),
    ERROR_PRODUCT(4,"Erro ao cadastrar o produto, confirme se preencheu todos os campos corretamento","Erro ao cadastrar produto"),
    ERROR_PRODUCT_STOCK(5,"Erro ao cadastrar o produto, confirme se preencheu o campo do stock do produto","Erro ao cadastrar produto"),
    ERROR_PRODUCT_PRICE(6,"Erro ao cadastrar o produto, confirme se preencheu o campo do valor do produto","Erro ao cadastrar produto"),
    ERROR_SALE(7,"Erro ao realizar venda, confirme se preencheu todos os campos corretamento","Erro ao realizar venda"),
    CUSTOMER_WITHDREW_FROM_SALE(88,"Cliente desistiu de realizar a compra do produto","Venda não realizada"),
    ERROR_GENDER(9,"Você deve selecionar entre Masculino ou Feminino","Erro ao selecionar Genêro"),
    ERROR_SALE_QUANTITY_STOCK(10,"Não possuímos essa quantidade no estoque, por favor digite a quantidade até:","Erro vender mais que o estoque"),
    ERROR_INITIAL(6,"O número informado, não corresponde com as opções: 1 para Sim e 2 para não","Erro ao iniciar programa"),

    ;

    private final Integer code;
    private final String detail;
    private final String message;

    CodeErrors(Integer code, String detail, String message) {
        this.code = code;
        this.detail = detail;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Erro {" +
                "Código = " + code +
                ", Mensagem ='" + message + '\'' +
                ", Detalhes = '" + detail + '\'' +
                '}';
    }
}
