# Restaurante API

API Java Spring Boot para gerenciamento de restaurante.

## Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- (Opcional) [Java 21+](https://adoptium.net/) e [Maven 3.9+](https://maven.apache.org/) para build manual

## Instalação e Execução

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/raclug/restaurante.git
   cd restaurante
    ```
2. **Configure a variáveis de ambiente:**

   Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis, alterando conforme necessário:

    ```env
    MYSQL_ROOT_PASSWORD=admin
    MYSQL_DATABASE=restaurante
    MYSQL_USER=restaurante_user
    MYSQL_PASSWORD=!@#$1234qW
    APP_DB_USER=restaurante_user
    APP_DB_PASS=!@#$1234qW
   ```
3. **Construa a imagem da aplicação:**

    ```bash
    docker build -t restaurante .
    ```

4. **Suba os containers com Docker Compose:**

    ```bash
    docker compose up -d
    ```
5. **Acesse a aplicação:**

   A API estará disponível em: http://localhost:8080

   A documentação Swagger estará disponível em: http://localhost:8080/swagger-ui.html


6. **Parar a aplicação:**

    ```bash
    docker compose down
    ```