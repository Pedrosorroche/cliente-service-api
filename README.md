# Cliente Service

Sistema de gestão de clientes com autenticação JWT, controle de permissões e integração com API de CEP. Desenvolvido com Vue 3 no frontend e Spring Boot no backend, seguindo os princípios da Clean Architecture.

## Tecnologias Utilizadas

### Backend
- Java 8
- Spring Boot 2.7.18
- Spring Security + JWT
- MySQL
- Clean Architecture (camadas: controller, service, use case, repository)

### Frontend
- Vue 3
- Axios
- FontAwesome
- Vite

---

## Funcionalidades

### Gerenciamento de Clientes
- Cadastro de clientes com múltiplos telefones e e-mails
- Máscara de CPF e CEP
- Preenchimento automático do endereço via API Brasil
- Edição e exclusão com confirmação

### Autenticação
- Login com geração de JWT
- Proteção de rotas
- Controle de acesso por roles (`ROLE_ADMIN` e `ROLE_USER`)
  - `ROLE_ADMIN`: Pode criar, editar e excluir clientes
  - `ROLE_USER`: Apenas leitura

### Navegação
- Tela de login
- Tela de listagem de clientes
- Modal para criação/edição de clientes
- Logout

---

## Como Executar

### Backend (Spring Boot)

1. Configure seu banco MySQL:

```sql
CREATE DATABASE db_cliente_service;
```

2. Configure o `application.yml` com suas credenciais:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_cliente_service
    username: root
    password: sua_senha
```

3. Compile e rode a aplicação:

```bash
./mvnw spring-boot:run
```

### Frontend (Vue 3)

1. Instale as dependências:

```bash
npm install
```

2. Configure o Axios em `/src/services/axios.js`:

```js
baseURL: 'http://localhost:8080'
```

3. Rode o servidor de desenvolvimento:

```bash
npm run dev
```

---

## Credenciais de Teste

| Tipo de Usuário | Username | Senha       | Permissões       |
|------------------|----------|-------------|------------------|
| Admin            | admin    | 123qwe!@#   | CRUD completo    |
| Usuário comum    | padrão   | 123qwe123   | Somente leitura  |

---

## Estrutura do Projeto

### Backend

```
cliente-service/
├── application/usecases
├── config/
├── domain/
├── infrastructure/
├── utils/
└── web/
```

### Frontend

```
frontend/
├── components/
│   └── ClienteModal.vue
├── pages/
│   └── ClientesView.vue
├── router/
├── services/
└── App.vue
```

---

## API Brasil

Endpoint para consulta de CEP:

```
GET https://brasilapi.com.br/api/cep/v1/{cep}
```

---

## Rotas da API

### Autenticação

**POST /auth/login**

**Request:**
```json
{
  "username": "admin",
  "senha": "123qwe!@#"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin",
  "roles": ["ROLE_ADMIN"]
}
```

---

### Clientes (Base URL: /clientes)

#### Criar Cliente [ROLE_ADMIN]

**POST /**

```json
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "emails": ["joao@email.com"],
  "telefones": ["(11) 99999-9999"],
  "endereco": {
    "cep": "01001-000",
    "logradouro": "Praça da Sé",
    "numero": "100",
    "complemento": "Lado ímpar",
    "bairro": "Sé",
    "cidade": "São Paulo",
    "estado": "SP"
  }
}
```

#### Listar Clientes [ROLE_ADMIN, ROLE_USER]

**GET /**

#### Buscar Cliente por ID [ROLE_ADMIN, ROLE_USER]

**GET /{id}**

#### Atualizar Cliente [ROLE_ADMIN]

**PUT /{id}**

#### Excluir Cliente [ROLE_ADMIN]

**DELETE /{id}**

---

## Códigos de Resposta

| Código | Descrição               |
|--------|--------------------------|
| 200    | OK                      |
| 201    | Created                 |
| 204    | No Content              |
| 400    | Bad Request             |
| 401    | Unauthorized            |
| 403    | Forbidden               |
| 404    | Not Found               |
| 500    | Internal Server Error   |
