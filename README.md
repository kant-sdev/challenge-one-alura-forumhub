# 🧵 ForumHub API – Versão 1.0

API REST desenvolvida em **Java 21 + Spring Boot**, criada como solução para o **Desafio ForumHub**.  
O projeto simula um sistema de fórum, com **gestão de usuários**, **tópicos**, **autenticação JWT**, **regras de negócio bem definidas** e **soft delete**, seguindo **boas práticas de arquitetura, organização de código e padrões de projeto**.

---

## 🎯 Objetivo do Projeto

Construir uma API segura e organizada que permita:

- Cadastro e autenticação de usuários
- Criação, listagem, atualização e exclusão lógica (soft delete) de tópicos
- Aplicação de regras de negócio reais
- Controle de acesso via JWT
- Persistência segura em banco relacional

O foco não foi apenas “fazer funcionar”, mas **estruturar corretamente o projeto pensando em manutenção, escalabilidade e clareza de responsabilidades**.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Flyway Migration**
- **Maven**

---

## 🧠 Boas Práticas Aplicadas

✔️ **Separação de responsabilidades (SRP)**  
✔️ **Arquitetura em camadas (API / Domain / Infra)**  
✔️ **DTOs para entrada e saída de dados**  
✔️ **Entidades JPA não expostas diretamente**  
✔️ **Regras de negócio centralizadas no domínio**  
✔️ **Validações desacopladas dos controllers**  
✔️ **Soft delete ao invés de exclusão física**  
✔️ **JWT stateless authentication**  
✔️ **Versionamento de banco com Flyway**  
✔️ **Uso de enums para estados e tipos de domínio**

---

## 🏗️ Arquitetura e Organização do Projeto

### 📁 Estrutura de Pastas

```
src/main/java/com/backend/forumhub
├── 📁 api
│   ├── 📁 controller
│   ├── 📁 doc
│   └── 📁 exception
├── 📁 common
│   ├── 📁 dto
│   │   ├── 📁 auth
│   │   ├── 📁 topic
│   │   ├── 📁 user
│   │   └── ☕ ApiStatusResponseDTO.java
├── 📁 domain
│   ├── 📁 exception
│   ├── 📁 model
│   │   ├── 📁 enums
│   │   ├── ☕ TopicModel.java
│   │   └── ☕ UserModel.java
│   ├── 📁 repository
│   ├── 📁 service
│   └── 📁 validations
├── 📁 infra
│   ├── 📁 config
│   └── 📁 security
└── ☕ ForumhubApplication.java
```

---

## 📐 Explicação da Arquitetura

### 🔹 `api` – Camada de Entrada (Web)

Responsável apenas por **receber e responder requisições HTTP**.

- `controller`: endpoints REST
- `exception`: tratamento de erros HTTP
- `doc`: reservado para documentação futura (Swagger)

👉 Controllers **não possuem regra de negócio**.

---

### 🔹 `common` – Camada Compartilhada

- `dto`: objetos de Request/Response
- `mapper`: conversões entre entidades e DTOs

👉 Evita acoplamento entre API e domínio.

---

### 🔹 `domain` – Núcleo da Aplicação

Onde ficam as **regras de negócio reais**.

- `model`: entidades JPA
- `enums`: status, tipos de fórum e tópicos
- `repository`: acesso a dados
- `service`: lógica de negócio
- `validations`: regras específicas (ex: permissões, estados)
- `exception`: exceções de domínio

👉 Camada independente de framework web.

---

### 🔹 `infra` – Infraestrutura Técnica

- `security`: JWT, filtros, UserDetails, SecurityConfig
- `config`: beans e configurações globais

👉 Tudo que é técnico e transversal.

---

## 🔐 Segurança e Autenticação

- Autenticação via **JWT**
- Filtro customizado (`JwtAuthenticationFilter`)
- Sessão **STATELESS**
- Password hashing com **BCrypt**
- Controle de acesso por endpoint

📌 **O token permanece válido até expirar**, mesmo após reiniciar a aplicação.

---
## ⚙️ Funcionamento Geral da API

A API segue o padrão **RESTful**, com autenticação baseada em **JWT (Bearer Token)** e fluxo típico de aplicações backend modernas.

### 🔐 Fluxo de Autenticação

1. O usuário realiza **login** enviando email e senha.
2. O Spring Security autentica via `AuthenticationManager`.
3. Um **JWT** é gerado contendo:
    - ID do usuário
    - Email
    - Role
4. O token é retornado ao cliente.
5. Todas as rotas protegidas exigem o token no header:

```
Authorization: Bearer <token>
```

📌 O token **continua válido mesmo após reiniciar a aplicação**, enquanto não expirar.

---


## 👤 Funcionalidades de Usuário

### ✔️ Cadastro
- Email único
- Senha criptografada
- Usuário ativo por padrão

### ✔️ Login
- Geração de JWT
- Autenticação via Spring Security

---

## 🧵 Funcionalidades de Tópicos

### ✔️ Criar tópico
- Usuário autenticado obrigatório
- Regras de negócio aplicadas
- Autor definido automaticamente pelo token

### ✔️ Listar tópicos
- Retorna apenas tópicos **não deletados**
- Uso de DTO simplificado

### ✔️ Atualizar tópico (PATCH)
Campos permitidos:
- Título
- Mensagem
- Status (ABERTA / RESOLVIDA)

Campos imutáveis:
- Tipo de fórum
- Tipo de tópico
- Autor

---

### ✔️ Soft Delete
- Campo `deleted` (boolean)
- Exclusão lógica
- Registro mantido no banco
- Não aparece em listagens

---

## 🛢️ Banco de Dados e Flyway

Migrations versionadas:

```
src/main/resources/db/migration
├── V1__create-table-user.sql
├── V2__alter-table-user.sql
├── V3__create-table-topics.sql
└── V4__alter-table-topics.sql
```

✔️ Histórico de alterações  
✔️ Execução automática no startup  
✔️ Consistência entre ambientes

---

## ▶️ Como Executar o Projeto

### Pré-requisitos
- Java 21+
- PostgreSQL
- Maven

### Passos
```bash
git clone https://github.com/seu-usuario/forumhub.git
cd forumhub
mvn clean install
mvn spring-boot:run
```

Configure o `application.properties` com o banco de dados.

---

## 📌 Status do Projeto

✅ **Versão 1.0 concluída**  
Todos os requisitos iniciais do desafio foram atendidos.

---

## 🔮 Possíveis Evoluções Futuras

- Paginação
- Swagger/OpenAPI
- Perfis administrativos
- Moderação de tópicos
- Testes automatizados
- Auditoria de ações

---

## 👨‍💻 Autor

**Kant.sdev**  
Projeto educacional – Desafio ForumHub  
