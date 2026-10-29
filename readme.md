# API Marketplace — Testes e Entrega Contínua

Projeto desenvolvido com **Java e Spring Boot** para aprofundar práticas de testes automatizados, arquitetura de software e integração contínua, explorando desde testes unitários até testes de integração com infraestrutura baseada em containers.

O projeto utiliza uma arquitetura modular com Maven e ferramentas que permitem validar o comportamento da aplicação, testar a comunicação entre componentes e automatizar processos de build e publicação de imagens Docker.

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.4.6**
- **Maven** — gerenciamento de dependências e organização multimódulo
- **JUnit 5, Mockito e AssertJ** — testes unitários e validações
- **Spring Boot Test e MockMvc** — testes da camada web
- **Rest Assured** — testes de integração via HTTP
- **Testcontainers** — execução de dependências em containers Docker
- **WireMock** — simulação de APIs externas
- **H2 e PostgreSQL** — testes com banco em memória e banco relacional
- **Flyway** — gerenciamento de migrações de banco de dados
- **LocalStack e AWS S3** — testes de integrações com serviços AWS
- **RabbitMQ e Redis** — testes de mensageria e cache
- **MapStruct** — mapeamento entre DTOs e entidades
- **Docker e Docker Compose** — containerização e gerenciamento de ambientes
- **GitHub Actions** — integração contínua e automação de builds
- **Docker Hub** — publicação de imagens Docker

## Principais funcionalidades e conceitos praticados

### Testes unitários

- Desenvolvimento orientado por testes (TDD).
- Utilização de JUnit 5, Mockito e AssertJ para validar regras de negócio.
- Criação de testes parametrizados com `@ParameterizedTest`.
- Validação de exceções e mensagens de erro com `assertThrows`.
- Isolamento de dependências por meio de mocks.
- Aplicação de princípios de responsabilidade única e organização dos casos de uso.

### Testes de integração

- Testes de controllers utilizando `MockMvc` e Spring Boot Test.
- Testes de repositórios JPA com `@DataJpaTest` e `TestEntityManager`.
- Utilização do H2 para testes isolados e rápidos.
- Testes com PostgreSQL real utilizando Testcontainers e Flyway.
- Testes HTTP com Rest Assured e execução em porta aleatória.
- Simulação de APIs externas com WireMock.
- Testes de integração com AWS S3 utilizando LocalStack.
- Validação de mensageria com RabbitMQ e Testcontainers.
- Testes de cache com Redis e `@Cacheable`.

### Arquitetura e organização do código

- Estudo da arquitetura hexagonal e da separação de responsabilidades.
- Organização do projeto em módulos Maven: `application`, `domain`, `infra` e `iandt`.
- Separação dos testes de integração em um módulo dedicado.
- Utilização de MapStruct para conversão entre DTOs e entidades.
- Configuração de ambientes de teste e isolamento de dependências.

### Integração contínua e entrega

- Configuração de workflows no GitHub Actions.
- Automação de testes e builds Maven.
- Configuração de secrets para proteger credenciais.
- Construção de imagens Docker.
- Publicação de imagens no Docker Hub por meio do GitHub Actions.
- Utilização de Docker Compose para gerenciar containers e dependências.

## Estrutura do projeto

```text
api-marketplace-testes-integracao/
├── application/   # Camada de aplicação
├── domain/        # Domínio e regras de negócio
├── infra/         # Infraestrutura e integrações
├── iandt/         # Testes de integração
├── pom.xml        # Configuração Maven principal
└── .github/
    └── workflows/ # Pipelines do GitHub Actions
```

## Executando os testes

Os testes são gerenciados pelo Maven. Para executar a suíte padrão:

```bash
mvn test
```

Para executar os testes de integração, utilize o perfil configurado no projeto:

```bash
mvn verify -PintegrationTests
```

**Pré-requisitos:** JDK 21, Maven e Docker em execução para os testes que utilizam Testcontainers ou serviços containerizados.

> Os comandos devem corresponder aos perfis e às configurações efetivamente definidos no `pom.xml`. Testes de integração podem exigir configuração adicional de ambiente.

## Objetivo do projeto

Consolidar conhecimentos de qualidade de software no desenvolvimento backend com Java, aplicando estratégias de testes unitários e de integração, organização modular, isolamento de dependências e automação de pipelines para tornar o processo de desenvolvimento e entrega mais confiável.
