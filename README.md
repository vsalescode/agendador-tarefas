# ⏰ Agendador de Tarefas – Microserviço Agendador

![Java](https://img.shields.io/badge/Java-17+-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green)
![JWT](https://img.shields.io/badge/Security-JWT-orange)
![OpenFeign](https://img.shields.io/badge/Communication-OpenFeign-informational)
![Build](https://img.shields.io/badge/Build-Maven-blueviolet)
![Status](https://img.shields.io/badge/Status-Completo-success)

Microserviço responsável pelo **agendamento, gerenciamento e controle de status de tarefas**, fazendo parte de uma **arquitetura de microserviços**, com foco em **segurança**, **escalabilidade** e **baixo acoplamento**.

A autenticação é realizada via **JWT**, reutilizando o token gerado pelo **Microserviço de Usuário**, garantindo integração segura entre os serviços.

---

## 🧱 Papel na Arquitetura

```text
[BFF]
  ├── Usuario Service (Autenticação / JWT)
  ├── Agendador Service (Este serviço)
  ├── Notificacao Service (Futuro)
  └── Comunicação via OpenFeign
```

---

## 🔐 Segurança e Autenticação

```
Authorization: Bearer <token>
```

---

## 📌 Funcionalidades

- Criar tarefas agendadas
- Buscar tarefas por usuário autenticado
- Buscar tarefas por período
- Atualizar tarefas
- Alterar status de notificação
- Deletar tarefas
- Comunicação com microserviço de Usuário via OpenFeign

---

## 🚀 Endpoints

| Método | Endpoint | Descrição |
|------|--------|---------|
| POST | /tarefas | Criar tarefa |
| GET | /tarefas | Listar tarefas do usuário |
| GET | /tarefas/eventos | Buscar tarefas por período |
| PUT | /tarefas?id= | Atualizar tarefa |
| PATCH | /tarefas?status=&id= | Alterar status |
| DELETE | /tarefas?id= | Deletar tarefa |

---

## 🗄️ Banco de Dados

- MongoDB
- Coleção: tarefa

---

## 🛠️ Tecnologias

- Java 17+
- Spring Boot
- Spring Security
- JWT
- Spring Data MongoDB
- Spring Cloud OpenFeign
- Lombok
- Maven

---

## ▶️ Executando Localmente

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🛣️ Roadmap

- ✅ CRUD de tarefas
- ✅ Autenticação JWT
- 🔜 Notificação
- 🔜 BFF
- 🔜 Docker
- 🔜 Testes
