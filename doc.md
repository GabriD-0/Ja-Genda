## Projeto: Mini Agendador (CRUD + regras simples)

**Back-end (Java):** Spring Boot REST API (Java 21)
**Front-end (Vue):** Vue 3 + Vite + Axios + Vue Router
**Banco:** SQLite

---

## Estado atual do projeto (atualizado)

### Back-end — já feito
* Projeto Spring Boot criado (`backend/`) com Maven.
* **Spring Boot 4.0.2**, **Java 21**.
* Dependências no `pom.xml`:
  * `spring-boot-starter-webmvc`
  * `spring-boot-starter-data-jpa`
  * `spring-boot-starter-validation`
  * Lombok
  * **SQLite**: `sqlite-jdbc` + `hibernate-community-dialect`
  * Testes: `spring-boot-starter-*-test` (data-jpa, validation, webmvc)
* `application.properties` configurado para **SQLite**:
  * `spring.datasource.url=jdbc:sqlite:agenda.db`
  * `spring.jpa.hibernate.ddl-auto=update`
  * `spring.jpa.show-sql=true`
* Classe principal: `JaGendaApplication.java`.

### Back-end — ainda não implementado
* Entidades (`Customer`, `Appointment`).
* Repositories, Services, Controllers.
* DTOs, validação (Bean Validation), tratamento de erro global (ControllerAdvice).
* Regra de conflito de horário (409).
* CORS para o front.

### Front-end
* Ainda não criado.

### Funcionalidades mínimas

* **Clientes**: criar, listar, editar, deletar
* **Agendamentos**: criar, listar por dia, cancelar
* **Regra simples**: não permitir **dois agendamentos no mesmo horário**

---

## Arquitetura
O doc já indica uma arquitetura em camadas:
 - Controllers → recebe HTTP, chama Service.
 - Services → regras de negócio, orquestra.
 - Repositories → acesso a dados (JPA).
 - Entities → entidades (modelo).
 - DTO → objetos de entrada/saída da API.

## 1 — Setup + base do Back-end (Java)

### Objetivos:

API rodando com 2 entidades e CRUD básico.

### Tarefas

1. ~~Criar projeto Spring Boot (dependências)~~ ✅ feito:
   * Spring Web (`spring-boot-starter-webmvc`)
   * Spring Data JPA
   * Validation
   * **SQLite** (escolhido em vez de H2; `sqlite-jdbc` + `hibernate-community-dialect`)

2. Modelar entidades:
   * `Customer { id, name, phone }`
   * `Appointment { id, customerId (ou relation), dateTime, status }` (status: SCHEDULED/CANCELED)

3. Criar:
   * Repositories (JPA)
   * Services (regras de negócio)
   * Controllers (rotas REST)

4. Validar entrada (Bean Validation):
   * nome obrigatório, telefone obrigatório
   * `dateTime` obrigatório

5. Testar com Insomnia/Postman

### Rotas sugeridas

* `GET /customers`

* `POST /customers`

* `PUT /customers/{id}`

* `DELETE /customers/{id}`

* `GET /appointments?date=YYYY-MM-DD`

* `POST /appointments`

* `PATCH /appointments/{id}/cancel`

**Definition of Done:** CRUD de clientes + criação/listagem de agendamentos funcionando via Postman.

---

## 2 — Regras + melhorias de API

### Objetivos:

Deixar a API “de verdade”: regra de conflito, erros decentes, DTOs.

### Tarefas

1. Implementar regra:

   * Ao criar agendamento: se já existe agendamento **SCHEDULED** com o mesmo `dateTime`, retornar **409 Conflict**
2. Implementar DTOs:

   * `CreateCustomerDTO`, `CreateAppointmentDTO`
3. Tratamento de erro global (ex: `@ControllerAdvice`)

   * retornar JSON com `message`, `timestamp`, `path`
4. CORS liberado pro front (localhost do Vue)
5. (Opcional) Swagger/OpenAPI pra documentar

**Definition of Done:** API com validação + conflitos e respostas de erro padronizadas.

---

## 3 — Front-end Vue (telas + consumo da API)

### Objetivos:

Front navegável consumindo o back.

### Tarefas

1. Criar projeto Vue 3 (Vite)

2. Dependências:
   * axios
   * vue-router

3. Páginas:
   * **Clientes**: lista + modal/form de criar/editar + deletar
   * **Agenda do dia**: selecionar data, listar agendamentos do dia
   * **Novo agendamento**: form (cliente + data/hora)

4. Criar `api.js` com baseURL e métodos:
   * `getCustomers`, `createCustomer`, etc.

5. UX básica:
   * Loading
   * Toast simples ou alert (por enquanto) pra erros (409, 400)

**Definition of Done:** Tudo que funciona no Postman agora funciona pelo Vue.

---

## 4 — Polimento + deploy local + README

### Objetivos:

Entregar como projeto apresentável (mesmo simples).

### Tarefas

1. Ajustar UI:
   * tabela organizada
   * formulário com validação básica no front

2. Melhorar status de agendamento:
   * mostrar “Cancelado” e não remover da lista (opcional)

3. Docker (opcional, mas dá muito “ponto”):
   * `Dockerfile` do back
   * `Dockerfile` do front
   * `docker-compose.yml` (se usar Postgres)

4. Escrever README:
   * como rodar backend e frontend
   * endpoints principais
   * prints do sistema

5. Checklist final:
   * cria cliente
   * cria agendamento
   * bloqueia horário duplicado
   * cancela agendamento

**Definition of Done:** Projeto rodando liso + README decente.

---

## Estrutura recomendada (pra iniciante não se perder)

### Back-end (Spring)

* `controller/`
* `service/`
* `repository/`
* `domain/` (entities)
* `dto/`
* `exception/`

### Front-end (Vue)

* `pages/Customers.vue`
* `pages/Appointments.vue`
* `pages/NewAppointment.vue`
* `services/api.js`
* `components/CustomerForm.vue` (opcional)

---

## Escopo extra (se sobrar tempo)

* Filtro por cliente
* ~~SQLite~~ ✅ já adotado como banco do projeto (ver item acima)
* Paginação simples no backend
* Autenticação fake (pin “1234” no front só pra treinar fluxo)
* SQLite