# 📞 API de Lista de Contatos

Esta é uma API RESTful simples para gerenciar contatos, permitindo **CRUD (Create, Read, Update, Delete)** de contatos.

---

## 🚀 Tecnologias Utilizadas
- **Java 23**
- **Spring Boot 3.4.3**
- **Spring Data JPA**
- **H2 Database** (banco de dados em memória)
- **Spring Validation** (Hibernate Validator)
- **Maven**

---

## 📦 Instalação e Configuração

### 🔹 1. Clone o Repositório
```sh
git clone https://github.com/seu-usuario/api-lista-contatos.git
cd api-lista-contatos
```

### 🔹 2. Configuração do Banco de Dados (H2)
Não é necessário configurar um banco de dados externo, pois o H2 Database roda na memória.

📍 **O H2 pode ser acessado no navegador:**
- **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:contatosdb`
- **Usuário:** `sa`
- **Senha:** (vazia por padrão)

📍 **Configuração no `application.properties`**:
```properties
# Configuração do H2 Database
spring.datasource.url=jdbc:h2:mem:contatosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Habilita o Console Web do H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Gera o banco automaticamente
spring.jpa.hibernate.ddl-auto=update
```

### 🔹 3. Compile e Execute a API
```sh
mvn spring-boot:run
```
A API estará disponível em **[http://localhost:8080/api/contacts](http://localhost:8080/api/contacts)**.

---

## 📌 Endpoints da API

### 🔹 1. Criar um Contato
**POST** `/api/v1/contacts/new`

📍 **Requisição:**
```json
{
  "name": "Caio Barbosa",
  "phone": "84999999999",
  "email": "caio@email.com"
}
```

✅ **Resposta (201 Created):**
```json
{
  "id": 1,
  "name": "Caio Barbosa",
  "phone": "84999999999",
  "email": "caio@email.com"
}
```

---

### 🔹 2. Listar Todos os Contatos
**GET** `/api/v1/contacts`

✅ **Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Caio Barbosa",
    "phone": "84999999999",
    "email": "caio@email.com"
  },
  {
    "id": 2,
    "name": "Ana Souza",
    "phone": "84988888888",
    "email": "ana@email.com"
  }
]
```

---

### 🔹 3. Buscar Contato por ID
**GET** `/api/v1/contacts/{id}`

📍 **Exemplo:**
```sh
GET /api/v1/contacts/1
```

✅ **Resposta (200 OK):**
```json
{
  "id": 1,
  "name": "Caio Barbosa",
  "phone": "84999999999",
  "email": "caio@email.com"
}
```

---

### 🔹 4. Atualizar um Contato
**PUT** `/api/v1/contacts/{id}`

📍 **Requisição:**
```json
{
  "name": "Caio Felipe",
  "phone": "84999999999",
  "email": "caio@email.com"
}
```

✅ **Resposta (200 OK):**
```json
{
  "id": 1,
  "name": "Caio Felipe",
  "phone": "84999999999",
  "email": "caio@email.com"
}
```

---

### 🔹 5. Deletar um Contato
**DELETE** `/api/v1/contacts/{id}`

📍 **Exemplo:**
```sh
DELETE /api/contacts/1
```

✅ **Resposta (200 OK):**
```json
{
  "message": "Contato deletado com sucesso!"
}
```

---

## ✉️ Contato
Caso tenha alguma dúvida ou sugestão, entre em contato pelo e-mail **caiofelipebarbosa92@gmail.com** ou abra uma *issue* no repositório.

---

🚀 Desenvolvido com **Spring Boot** por [Caio Barbosa](https://github.com/caiofmbarbosa)

