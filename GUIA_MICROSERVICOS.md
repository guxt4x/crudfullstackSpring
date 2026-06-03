# 🚀 Guia de Inicialização - Arquitetura de Microserviços

## 📋 Resumo da Arquitetura

Sua aplicação foi dividida em **6 microserviços independentes**, cada um rodando em uma porta diferente:

| Serviço | Porta | Responsabilidade | Banco de Dados |
|---------|-------|-----------------|----------------|
| **Pessoa Service** | 8082 | Gerenciar pessoas | H2 (pessoadb) |
| **Matricula Service** | 8081 | Gerenciar matrículas | H2 (matriculadb) |
| **Disciplina Service** | 8083 | Gerenciar disciplinas | H2 (disciplinadb) |
| **Curso Service** | 8084 | Gerenciar cursos | H2 (cursodb) |
| **Turma Service** | 8085 | Gerenciar turmas | H2 (turmadb) |
| **Usuario Service** | 8086 | Gerenciar usuários | H2 (usuariodb) |
| **Frontend (Angular)** | 4200 | Interface Web | - |

---

## 🎯 Opção 1: Executar Localmente (Maven)

### Pré-requisitos
- ✅ Java 17+
- ✅ Maven 3.6+
- ✅ Node.js (para frontend)

### Passos

#### 1. **Compilar os microserviços**
```bash
cd microservicos
mvn clean install -DskipTests
```

#### 2. **Iniciar cada serviço em terminais diferentes**

**Terminal 1 - Pessoa Service:**
```bash
cd microservicos/pessoa-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8082

**Terminal 2 - Matricula Service:**
```bash
cd microservicos/matricula-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8081

**Terminal 3 - Disciplina Service:**
```bash
cd microservicos/disciplina-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8083

**Terminal 4 - Curso Service:**
```bash
cd microservicos/curso-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8084

**Terminal 5 - Turma Service:**
```bash
cd microservicos/turma-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8085

**Terminal 6 - Usuario Service:**
```bash
cd microservicos/usuario-service
mvn spring-boot:run
```
✅ Rodando em: http://localhost:8086

#### 3. **Iniciar o Frontend (novo terminal)**
```bash
cd frontend
npm install
npm start
```
✅ Frontend rodando em: http://localhost:4200

---

## 🐳 Opção 2: Executar com Docker Compose

### Pré-requisitos
- ✅ Docker
- ✅ Docker Compose

### Passos

#### 1. **Dentro da pasta microservicos**
```bash
cd microservicos
docker-compose up --build
```

#### 2. **Aguarde até ver algo como:**
```
pessoa-service      | Started PessoaServiceApplication in 8.5 seconds
matricula-service   | Started MatriculaServiceApplication in 8.2 seconds
disciplina-service  | Started DisciplinaServiceApplication in 7.9 seconds
curso-service       | Started CursoServiceApplication in 8.1 seconds
turma-service       | Started TurmaServiceApplication in 8.3 seconds
usuario-service     | Started UsuarioServiceApplication in 8.0 seconds
```

#### 3. **Iniciar o Frontend (em outro terminal)**
```bash
cd frontend
npm install
npm start
```

#### Para parar todos os serviços:
```bash
docker-compose down
```

---

## 🌐 Acessando os Serviços

### **Frontend**
```
http://localhost:4200
```

### **Consoles H2 dos Bancos de Dados**
- Pessoa Service: http://localhost:8082/h2-console
- Matricula Service: http://localhost:8081/h2-console
- Disciplina Service: http://localhost:8083/h2-console
- Curso Service: http://localhost:8084/h2-console
- Turma Service: http://localhost:8085/h2-console
- Usuario Service: http://localhost:8086/h2-console

**Credenciais H2:**
- Usuário: `sa`
- Senha: `sa`
- JDBC URL: `jdbc:h2:mem:<nomebanco>` (veja arquivo application.properties)

### **Endpoints dos Serviços**

**Pessoa Service:**
```bash
GET  http://localhost:8082/api/pessoas
POST http://localhost:8082/api/pessoas
GET  http://localhost:8082/api/pessoas/{id}
```

**Matricula Service:**
```bash
GET  http://localhost:8081/api/matriculas
POST http://localhost:8081/api/matriculas
GET  http://localhost:8081/api/matriculas/{id}
```

E assim por diante para os outros serviços...

---

## 🔗 Comunicação Entre Microserviços

Se precisa fazer chamadas de um serviço para outro, use as variáveis de ambiente:

```java
@Value("${pessoa.service.url:http://pessoa-service:8082}")
private String pessoaServiceUrl;

// Em desenvolvimento local:
// http://localhost:8082

// Em Docker/Kubernetes:
// http://pessoa-service:8082
```

---

## 🧪 Testando com CURL

```bash
# Criar uma pessoa
curl -X POST http://localhost:8082/api/pessoas \
  -H "Content-Type: application/json" \
  -d '{"nome":"João","idade":25}'

# Listar pessoas
curl http://localhost:8082/api/pessoas

# Acessar console H2
# Abrir no navegador: http://localhost:8082/h2-console
```

---

## 📝 Próximos Passos Recomendados

1. **API Gateway (Spring Cloud Gateway)**
   - Centralizar todas as requisições em uma única porta
   - Exemplo: `http://localhost:8000/pessoas`, `http://localhost:8000/matriculas`

2. **Service Discovery (Eureka)**
   - Registrar e descobrir serviços automaticamente

3. **Circuit Breaker (Resilience4j)**
   - Tratar falhas entre chamadas de serviços

4. **Centralized Logging (ELK Stack)**
   - Logs centralizados de todos os serviços

5. **Monitoramento (Prometheus + Grafana)**
   - Métricas de performance e saúde dos serviços

---

## ✅ Checklist de Inicialização

- [ ] Java 17+ instalado
- [ ] Maven compilando com sucesso
- [ ] Todos os 6 serviços iniciando sem erros
- [ ] Frontend rodando em 4200
- [ ] Acessar http://localhost:4200 no navegador
- [ ] Validar alguns endpoints via CURL
- [ ] Acessar consoles H2 para verificar bancos

---

**Dúvidas? Qualquer erro? Avise!** 🚀
