# Customer Transactions

## Descrição
Este projeto implementa um sistema de transações financeiras associadas a contas de clientes. Cada cliente possui uma conta e, para cada operação realizada, uma transação é criada e vinculada à respectiva conta. As transações podem representar diferentes tipos de operações, como compras, parcelamentos e saques.

## Funcionalidades
- **Gerenciamento de contas de clientes**: Criação e gerenciamento das contas dos clientes.
- **Registro de transações**: Cada operação (como compras e saques) gera uma transação que é associada a uma conta de cliente.
- **Suporte a múltiplos tipos de transação**:
  - Compra
  - Compra parcelada
  - Saque
  - Pagamento de dívida (transações de crédito)
- **Validação de transações**: Transações de compra, compra parcelada e saque são registradas como valores negativos (dívida), enquanto pagamentos são registrados como valores positivos (crédito).

## Modelos de Dados
### Conta (`Account`)
- **ID**: Identificador único da conta.
- **Cliente**: Cliente proprietário da conta.
- **Saldo**: Total de saldo disponível na conta.

### Transação (`Transaction`)
- **ID**: Identificador único da transação.
- **Tipo de Transação**: O tipo de operação (compra, compra parcelada, saque, pagamento).
- **Valor**: O valor da transação (negativo para compras, positivo para pagamentos).
- **Data de Criação**: Data e hora em que a transação foi criada.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.3.4**: Framework para a construção da aplicação.
- **Spring Data JPA**: Para gerenciamento de banco de dados relacional e persistência de dados.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **Springdoc OpenAPI**: Para a documentação automática das APIs.
- **Logback**: Para o gerenciamento de logs da aplicação.
- **Lombok**: Para reduzir código boilerplate no projeto.

## Requisitos
- JDK 21
- Maven 3.x

## Executando a Aplicação
1. Clone o repositório:
    ```bash
    git clone https://github.com/pedroppd/pismo-challenger.git
    ```
2. Acesse o diretório do projeto:
    ```bash
    cd customer-transactions
    ```
3. Execute a aplicação com Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API
A documentação da API está disponível via OpenAPI/Swagger.
- **Swagger documentação** - http://localhost:8080/swagger-ui/index.html
- **Endpoints em formato JSON** - http://localhost:8080/v3/api-docs
