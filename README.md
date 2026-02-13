# 🟠 Serviço de Tarefas (Core Service)

O **"Coração" do negócio**.\
Responsável pela persistência, gerenciamento de estado e regras de
negócio das tarefas agendadas, utilizando a flexibilidade de um banco
NoSQL.

------------------------------------------------------------------------

## 🚀 Visão Geral

Este microsserviço é o centro de armazenamento de dados do sistema.\
Ele recebe comandos do BFF e armazena as tarefas em um banco de dados
**MongoDB**.

Diferente do serviço de Usuário (relacional), este serviço foi projetado
para ser:

-   Performático em leitura e escrita
-   Eficiente em consultas por intervalo de tempo
-   Escalável para suportar alto volume de eventos

Essencial para o funcionamento do sistema de agendamento automático.

------------------------------------------------------------------------

## ✅ Principais Responsabilidades

-   🍃 **Persistência NoSQL:** Armazenamento escalável e schemaless
-   ⏱️ **Consultas Temporais:** Busca otimizada por intervalo de datas
-   🔐 **Validação de Token:** Verificação de JWT nas requisições
-   🔄 **Ciclo de Vida:** Controle de status (PENDENTE → NOTIFICADO →
    CANCELADO)

------------------------------------------------------------------------

## 🛠️ Tecnologias Utilizadas

-   Java 17
-   Spring Boot 3
-   MongoDB
-   Spring Data MongoDB
-   Spring Cloud OpenFeign
-   MapStruct
-   Lombok

------------------------------------------------------------------------

## ⚙️ Configuração (MongoDB)

O serviço roda na porta **8081**.

Arquivo:

    src/main/resources/application.properties

### Exemplo de Configuração

``` properties
spring.application.name=agendador-tarefas
server.port=8081

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/db_agendador

# Integração com Serviço de Usuário
usuario.url=http://localhost:8080
```

> Certifique-se de que o MongoDB está rodando na porta padrão 27017.

------------------------------------------------------------------------

## 🔌 Endpoints

### 📌 Gerenciamento de Tarefas

  Método   Rota         Descrição
  -------- ------------ --------------------------
  POST     `/tarefas`   Cria nova tarefa
  GET      `/tarefas`   Lista tarefas do usuário
  PUT      `/tarefas`   Atualiza tarefa
  DELETE   `/tarefas`   Remove tarefa

------------------------------------------------------------------------

### ⚙️ Endpoints de Automação (Uso Interno)

  Método   Rota                 Descrição
  -------- -------------------- --------------------------------------
  GET      `/tarefas/eventos`   Busca tarefas por intervalo de tempo
  PATCH    `/tarefas`           Atualiza apenas o status

------------------------------------------------------------------------

## 🍃 Modelo de Dados (MongoDB)

Coleção: **tarefa**

### Exemplo de Documento

``` json
{
  "_id": "65b2f8a1e4b0a1b2c3d4e5f6",
  "nomeTarefa": "Dentista",
  "descricao": "Consulta de rotina com Dr. Silva",
  "emailUsuario": "usuario@exemplo.com",
  "dataCriacao": "2024-01-25T10:00:00",
  "dataEvento": "2024-01-30T14:30:00",
  "dataAlteracao": null,
  "statusNotificacaoEnum": "PENDENTE"
}
```

------------------------------------------------------------------------

## 🔎 Consulta Estratégica no Repositório

Método otimizado para o Cron Job:

``` java
List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(
    LocalDateTime dataInicial, 
    LocalDateTime dataFinal, 
    StatusNotificacaoEnum status
);
```

Essa consulta permite buscar tarefas pendentes dentro de um intervalo
específico de tempo.

------------------------------------------------------------------------

## 🔐 Segurança

O serviço implementa um **Filtro JWT (JwtRequestFilter)**:

1.  Intercepta requisições
2.  Extrai token Bearer do header
3.  Valida assinatura
4.  Recupera e-mail do usuário

Opcionalmente, pode validar a existência do usuário via **Feign
(UsuarioClient)** antes de processar a operação.

------------------------------------------------------------------------

## ▶️ Como Executar

1.  Suba o MongoDB
2.  (Opcional) Suba o Serviço de Usuário (8080) para validação híbrida
3.  Execute:

``` bash
mvn spring-boot:run
```

Disponível em:

    http://localhost:8081

------------------------------------------------------------------------

## 👨‍💻 Autor

Desenvolvido por **João Victor**

🔗 [LinkedIn](https://www.linkedin.com/in/vsalescode/)
🌐 [Portfólio](https://portfolio-vsalescode.vercel.app/)
