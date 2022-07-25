# Azship Freight API

Uma API simples de cadastro e pesquisa de fretes. <br>
Pode ser usada com REST ou GraphQL.

## Powered by

* Spring Boot 2.6.7;
* Java 11;
* Docker;
* Docker-Compose;
* Flyway;
* MariaDB;
* Swagger/OpenAPI;
* outras libs opensource;

## Rest ou GraphQL
Você pode usá-la com endpoints REST ou GraphQL.

Para informações sobre os endpoints REST, vá para <http://localhost:8080/docs/ui>.

Se prefir o GraphQL, <http://localhost:8080/graphiql>.

## Como rodar
Basta ter o docker-compose instalado. Com isso, entre na pasta ```./docker``` e rode o comando ```docker-compose up``` ou ```docker-compose up -d```.

O arquivo de configuração subirá um banco de dados MariaDB e a aplicação Spring Boot.

## Banco de dados
Pode ocorrer algum erro de configuração na inicialização do banco de dados. Se for o caso, você pode usar qualquer banco MariaDB de sua escolha.
<br> Basta editar as variáveis do arquivo [config.env](./env/config.env).

* ### Tabelas
    As tabelas a serem criadas estão no arquivo [V1.0.0__create_tables.sql](./src/main/resources/db/migration/V1.0.0__create_tables.sql).
