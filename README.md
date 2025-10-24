# API de Tarefas com Spring Boot

API REST simples para gerenciar tarefas (to‑do). Permite criar, listar, buscar por id, atualizar e excluir tarefas. O projeto demonstra uma arquitetura em camadas com Spring Boot e uso de JPA para persistência.

## Visão Geral
- Finalidade: gerenciamento de tarefas via HTTP
- Padrão: REST, JSON
- Porta padrão: `8080`

## Tecnologias
- Java 17+
- Spring Boot (Web, Data JPA)
- Banco em memória H2 (ambiente de desenvolvimento)
- Maven ou Gradle (wrappers inclusos: `mvnw`/`gradlew`)

## Arquitetura
- Controller: expõe endpoints REST e orquestra requisições
- Service: regras de negócio e validações
- Repository: acesso a dados via Spring Data JPA
- Entity/Model: representação do domínio (ex.: `Task`)

## Modelo de Dados (exemplo)
```
Task
- id: Long
- title: String
- description: String
- status: String (ex.: PENDING, DONE)
- dueDate: LocalDate
```

## Endpoints
- `POST /tasks` — cria uma tarefa
- `GET /tasks` — lista todas as tarefas
- `GET /tasks/{id}` — busca uma tarefa pelo id
- `PUT /tasks/{id}` — atualiza uma tarefa existente
- `DELETE /tasks/{id}` — remove uma tarefa

### Exemplo de payload (POST/PUT)
```
{
  "title": "Estudar Spring",
  "description": "Praticar CRUD com Spring Boot",
  "status": "PENDING",
  "dueDate": "2025-10-23"
}
```

### Códigos de status (padrão sugerido)
- 200/204 — sucesso em operações de leitura/remoção
- 201 — criado
- 400 — requisição inválida (validação)
- 404 — recurso não encontrado
- 409 — conflito (ex.: regras de negócio)

## Como Executar
Com Maven (Windows):
```
mvnw.cmd spring-boot:run
```

Com Maven (Linux/Mac):
```
./mvnw spring-boot:run
```

Com Gradle (Windows):
```
gradlew.bat bootRun
```

Com Gradle (Linux/Mac):
```
./gradlew bootRun
```

Após subir, a API responde em `http://localhost:8080`.

## Configuração (exemplo para H2)
Arquivo `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:h2:mem:tasksdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

## Exemplos cURL
- Criar:
```
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Estudar Spring","description":"CRUD","status":"PENDING","dueDate":"2025-10-23"}'
```

- Listar:
```
curl http://localhost:8080/tasks
```

- Buscar por id:
```
curl http://localhost:8080/tasks/1
```

- Atualizar:
```
curl -X PUT http://localhost:8080/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Estudar Spring","description":"CRUD","status":"DONE","dueDate":"2025-10-25"}'
```

- Remover:
```
curl -X DELETE http://localhost:8080/tasks/1
```

## Estrutura Sugerida do Projeto
```
src/
  main/
    java/
      com/example/tasks/
        controller/
        service/
        repository/
        model/
    resources/
      application.properties
```

## Próximos Passos (ideias)
- Validação com `jakarta.validation` e `@Valid`
- Paginação e filtros em `GET /tasks`
- Documentação com OpenAPI/Swagger UI
- DTOs e mapeamento (ex.: MapStruct)

## Referências
- Spring Boot: https://spring.io/projects/spring-boot
- Spring Initializr: https://start.spring.io/
- Spring Data JPA: https://spring.io/projects/spring-data-jpa
