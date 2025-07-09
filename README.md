# Spring Keycloak Training

Projeto de exemplo utilizando Spring Security com Keycloak.

## Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Como executar

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd spring-security-training/spring-keycloack
   ```

2. Suba os containers com Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. O serviço estará disponível em:
   - Aplicação Spring Boot: [http://localhost:8080](http://localhost:8080)
   - Keycloak: [http://localhost:9090](http://localhost:9090)

4. Para parar os containers:
   ```bash
   docker-compose down
   ```

## Observações

- As configurações de Keycloak e variáveis de ambiente podem ser ajustadas no arquivo `docker-compose.yml`.
- Consulte a documentação do projeto para detalhes de endpoints e autenticação.
