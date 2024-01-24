# Sistema de Vendas

Este é um sistema de vendas simples, desenvolvido em Java, para gerenciar operações de vendas de produtos. O sistema é interativo, permitindo a entrada de dados pelo usuário para realizar vendas, cadastrar produtos, clientes e vendedores.

## Funcionalidades

### 1. Iniciar Operação de Vendas

O sistema permite iniciar a operação de vendas, verificando se o usuário deseja prosseguir.

```java
public void startSalesOperation();
```

### 2. Iniciar Venda

O método `initSales()` inicia o processo de venda, verificando estoque de produtos e solicitando dados do cliente e vendedor.

```java
public void initSales();
```

### 3. Verificar Estoque de Produtos

O método `verifyStockProducts()` verifica se há produtos em estoque para venda.

```java
public void verifyStockProducts();
```

### 4. Processar Venda de Produto

O método `processSaleProduct()` inicia o processamento da venda de produtos, solicitando a quantidade desejada e calculando o valor total.

```java
private void processSaleProduct();
```

### 5. Cadastrar Produto

O método `registerProduct()` permite cadastrar um novo produto no sistema.

```java
private void registerProduct() throws ExceptionUtil;
```

### 6. Cadastrar Cliente

O método `registerClient()` inicia o cadastro de um novo cliente no sistema.

```java
public void registerClient();
```

### 7. Cadastrar Vendedor

O método `registerVendor()` inicia o cadastro de um novo vendedor no sistema.

```java
public void registerVendor() throws ExceptionUtil;
```

## Como Executar

Para executar o sistema, basta criar uma instância da classe `Sales` e chamar o método `startSalesOperation()`.

```java
public static void main(String[] args) {
    Sales sales = new Sales();
    sales.startSalesOperation();
}
```

## Requisitos

- Java (JRE) instalado no ambiente.

## Notas

Este sistema de vendas é uma aplicação de console interativo e foi desenvolvido como um projeto de treinamento. A interação com o usuário é feita principalmente através da entrada padrão (`System.in`) e mensagens no console.