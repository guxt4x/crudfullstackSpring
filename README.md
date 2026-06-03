# 🎓 Sistema de Cadastro Acadêmico - Arquitetura de Microserviços

Uma aplicação moderna de cadastro acadêmico utilizando **Spring Boot** e arquitetura de **microserviços**.

---

## 📋 Visão Geral

Este projeto foi refatorado de uma arquitetura monolítica para uma **arquitetura de microserviços**, permitindo escalabilidade, independência de deployment e facilidade de manutenção.

### Stack Tecnológico
- **Backend:** Java 17, Spring Boot 3.2.5
- **Frontend:** Angular 17+, TypeScript, Nginx
- **Banco de Dados:** H2 (desenvolvimento)
- **Orquestração:** Docker Compose
- **Padrões:** REST API, JWT, microserviços

---

## 🏗️ Arquitetura de Microserviços

### Serviços Disponíveis

| Serviço | Porta | Responsabilidade | Banco |
|---------|-------|-----------------|-------|
| **Pessoa Service** | 8082 | Gerenciar pessoas | H2 pessoadb |
| **Matricula Service** | 8081 | Gerenciar matrículas | H2 matriculadb |
| **Disciplina Service** | 8083 | Gerenciar disciplinas | H2 disciplinadb |
| **Curso Service** | 8084 | Gerenciar cursos | H2 cursodb |
| **Turma Service** | 8085 | Gerenciar turmas | H2 turmadb |
| **Usuario Service** | 8086 | Gerenciar usuários | H2 usuariodb |
| **Frontend (Angular)** | 4200 | Interface Web | - |

---

## 🚀 Quick Start

### Opção 1: Docker Compose (Recomendado)

```bash
# Na raiz do projeto
docker-compose up --build

# Em outro terminal, iniciar o frontend
cd frontend
npm install
npm start
```

Acesse: http://localhost:4200

### Opção 2: Execução Local (Maven)

```bash
# Compilar todos os microserviços
cd microservicos
mvn clean install -DskipTests

# Iniciar cada serviço (em terminais diferentes)
cd pessoa-service && mvn spring-boot:run
cd matricula-service && mvn spring-boot:run
cd disciplina-service && mvn spring-boot:run
cd curso-service && mvn spring-boot:run
cd turma-service && mvn spring-boot:run
cd usuario-service && mvn spring-boot:run

# Em outro terminal, iniciar o frontend
cd frontend
npm install
npm start
```

### Opção 3: Scripts de Inicialização

**Windows:**
```bash
./start-all-services.bat
```

**Mac/Linux:**
```bash
chmod +x start-all-services.sh
./start-all-services.sh
```

---

## 📚 Documentação

- **[GUIA_MICROSERVICOS.md](./GUIA_MICROSERVICOS.md)** - Guia completo com endpoints e exemplos
- **[CHECKLIST_CONFIGURACAO.md](./CHECKLIST_CONFIGURACAO.md)** - Próximos passos e melhorias
- **[microservicos/docker-compose.yml](./microservicos/docker-compose.yml)** - Docker Compose dos microserviços

---

## 📍 Endpoints da API

### Pessoa Service (8082)
```bash
GET    /api/pessoas          # Listar todas
POST   /api/pessoas          # Criar nova
GET    /api/pessoas/{id}     # Buscar por ID
PUT    /api/pessoas/{id}     # Atualizar
DELETE /api/pessoas/{id}     # Deletar
```

### Matricula Service (8081)
```bash
GET    /api/matriculas       # Listar todas
POST   /api/matriculas       # Criar nova
GET    /api/matriculas/{id}  # Buscar por ID
```

### Disciplina Service (8083)
```bash
GET    /api/disciplinas      # Listar todas
POST   /api/disciplinas      # Criar nova
GET    /api/disciplinas/{id} # Buscar por ID
```

### Curso Service (8084)
```bash
GET    /api/cursos           # Listar todos
POST   /api/cursos           # Criar novo
GET    /api/cursos/{id}      # Buscar por ID
```

### Turma Service (8085)
```bash
GET    /api/turmas           # Listar todas
POST   /api/turmas           # Criar nova
GET    /api/turmas/{id}      # Buscar por ID
```

### Usuario Service (8086)
```bash
GET    /api/usuarios         # Listar todos
POST   /api/usuarios         # Criar novo
GET    /api/usuarios/{id}    # Buscar por ID
```

---

## 🗄️ Consoles de Banco de Dados

H2 Console disponível em cada serviço:

- **Pessoa:** http://localhost:8082/h2-console
- **Matricula:** http://localhost:8081/h2-console
- **Disciplina:** http://localhost:8083/h2-console
- **Curso:** http://localhost:8084/h2-console
- **Turma:** http://localhost:8085/h2-console
- **Usuario:** http://localhost:8086/h2-console

**Credenciais:**
- User: `sa`
- Password: `sa`
- URL: `jdbc:h2:mem:<nomebanco>` (veja application.properties de cada serviço)

---

## 🧪 Testando com cURL

```bash
# Criar uma pessoa
curl -X POST http://localhost:8082/api/pessoas \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","idade":25}'

# Listar pessoas
curl http://localhost:8082/api/pessoas

# Buscar por ID
curl http://localhost:8082/api/pessoas/1

# Atualizar
curl -X PUT http://localhost:8082/api/pessoas/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Updated","idade":26}'

# Deletar
curl -X DELETE http://localhost:8082/api/pessoas/1
```

---

## 📁 Estrutura do Projeto

```
crudfullstackSpring/
├── microservicos/
│   ├── pessoa-service/          # Serviço de Pessoas
│   ├── matricula-service/       # Serviço de Matrículas
│   ├── disciplina-service/      # Serviço de Disciplinas
│   ├── curso-service/           # Serviço de Cursos
│   ├── turma-service/           # Serviço de Turmas
│   ├── usuario-service/         # Serviço de Usuários
│   ├── pom.xml                  # POM parent
│   └── docker-compose.yml       # Composição dos microserviços
├── frontend/
│   ├── src/
│   ├── angular.json
│   ├── Dockerfile
│   ├── nginx.conf
│   └── package.json
├── diagramas/                   # Diagramas das entidades
├── docker-compose.yml           # Composição completa (raiz)
├── GUIA_MICROSERVICOS.md        # Guia de inicialização
├── CHECKLIST_CONFIGURACAO.md    # Próximos passos
├── start-all-services.bat       # Script Windows
├── start-all-services.sh        # Script Linux/Mac
└── README.md                    # Este arquivo
```

---

## 🔧 Pré-requisitos

- Java 17+
- Maven 3.6+
- Node.js 16+
- Docker & Docker Compose (para Docker)

Verifique as instalações:
```bash
java -version
mvn -v
node -v
npm -v
docker --version
docker-compose --version
```

---

## 📦 Compilação

### Compilar todos os microserviços

```bash
cd microservicos
mvn clean install
```

### Compilar um serviço específico

```bash
cd microservicos/pessoa-service
mvn clean install
```

---

## 🚨 Solução de Problemas

### Erro de porta já em uso
```bash
# Windows
netstat -ano | findstr :8082

# Mac/Linux
lsof -i :8082
```

### Docker Compose não inicia
```bash
# Limpar volumes e redes
docker-compose down -v

# Reconstruir
docker-compose up --build
```

### Frontend não carrega
```bash
# Limpar cache npm
cd frontend
npm cache clean --force
npm install
npm start
```

---

## 🔮 Próximas Melhorias Recomendadas

1. **API Gateway** - Centralizar todas as requisições em uma porta única
2. **Service Discovery (Eureka)** - Registro automático de serviços
3. **Circuit Breaker (Resilience4j)** - Tratamento de falhas
4. **Centralized Logging** - ELK Stack para logs
5. **Monitoring** - Prometheus + Grafana
6. **CI/CD** - GitHub Actions ou GitLab CI
7. **Kubernetes** - Deployment em produção

Veja [CHECKLIST_CONFIGURACAO.md](./CHECKLIST_CONFIGURACAO.md) para detalhes.

---

## 📞 Suporte

Dúvidas ou problemas? Consulte:
- [GUIA_MICROSERVICOS.md](./GUIA_MICROSERVICOS.md)
- [CHECKLIST_CONFIGURACAO.md](./CHECKLIST_CONFIGURACAO.md)

---

## 📝 Licença

Este projeto é fornecido como material educacional.

---

**Desenvolvido com ❤️ para fins acadêmicos**