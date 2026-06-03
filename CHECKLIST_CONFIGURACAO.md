# 🔧 Checklist de Configuração - Microserviços

## ✅ Configurações Concluídas

- [x] **Serviços Divididos:** 6 microserviços independentes
- [x] **Portas Configuradas:** Cada serviço em uma porta diferente (8081-8086)
- [x] **Bancos de Dados:** H2 em memória para cada serviço
- [x] **Docker Compose:** Pronto para orquestração
- [x] **Scripts de Inicialização:** `.bat` (Windows) e `.sh` (Linux/Mac)

---

## 🚨 Próximos Passos IMPORTANTES

### 1. **Configurar Comunicação Entre Serviços** ⚡
Atualmente, cada serviço é independente. Se você precisa que eles se comuniquem (ex: Turma chamar Pessoa), faça:

**Adicionar dependências no pom.xml de cada serviço:**
```xml
<!-- Feign Client para comunicação com outros serviços -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>3.1.8</version>
</dependency>

<!-- Spring Cloud Load Balancer -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-loadbalancer</artifactId>
    <version>3.1.8</version>
</dependency>
```

**Exemplo: Turma Service chamando Pessoa Service**
```java
@FeignClient(name = "pessoa-service", url = "${pessoa.service.url}")
public interface PessoaClient {
    @GetMapping("/api/pessoas/{id}")
    PessoaDTO getPessoaById(@PathVariable Long id);
}
```

### 2. **Criar API Gateway** 🌐
Centralizar todas as requisições em uma porta única (ex: 8000):

**Dependências:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

**application.properties do Gateway:**
```properties
server.port=8000

spring.cloud.gateway.routes[0].id=pessoa
spring.cloud.gateway.routes[0].uri=http://localhost:8082
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/pessoas/**

spring.cloud.gateway.routes[1].id=matricula
spring.cloud.gateway.routes[1].uri=http://localhost:8081
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/matriculas/**

# ... similar para outros serviços
```

Assim, todas as requisições viriam por: http://localhost:8000

### 3. **Atualizar Frontend** 📱
Se criar um API Gateway, atualizar o `environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8000/api'  // Usar apenas um endpoint
};
```

Atualizar o `nginx.conf`:
```nginx
location /api/ {
    proxy_pass http://api-gateway:8000/api/;
}
```

### 4. **Autenticação/Autorização Centralizada** 🔐
Se todos precisam validar token JWT:

- Criar um **Auth Service** separado na porta 8090
- Todos os outros serviços chamam esse serviço para validar token
- Usar JWT com claims de roles/permissões

### 5. **Service Discovery (Eureka)** 📍
Para desenvolvimento mais robusto:

**Criar Eureka Server:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

**application.properties:**
```properties
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Cada microserviço se registra e descobre outros automaticamente.

---

## 📊 Arquitetura Recomendada (Melhorada)

```
┌─────────────────────────────────────────────┐
│         Frontend (Angular) - :4200          │
└──────────────┬──────────────────────────────┘
               │ /api
┌──────────────▼──────────────────────────────┐
│      API Gateway - :8000                    │
│  (Spring Cloud Gateway)                     │
└──────┬──────────┬──────────┬─────────┬──────┘
       │          │          │         │
       ▼          ▼          ▼         ▼
  ┌─────────┐┌──────────┐┌───────┐┌────────┐
  │ Pessoa  ││ Matricula││ Turma ││ Curso  │
  │ :8082   ││  :8081   ││ :8085 ││ :8084  │
  └─────────┘└──────────┘└───────┘└────────┘

┌─────────────────────────────────────────────┐
│   Auth Service (JWT Validation) - :8090    │
│   (Chamado por todos os outros)             │
└─────────────────────────────────────────────┘

┌─────────────────────────────────────────────┐
│   Eureka Server (Discovery) - :8761        │
│   (Registro centralizado de serviços)       │
└─────────────────────────────────────────────┘
```

---

## 🎯 Opções de Inicialização Recomendadas

### **Para Desenvolvimento Rápido** (Recomendado)
1. Use Docker Compose:
   ```bash
   cd microservicos
   docker-compose up --build
   ```

### **Para Debug Detalhado**
1. Use o script `start-all-services.bat` (Windows) ou `.sh` (Linux/Mac)
2. Abra cada serviço em uma janela separada
3. Use breakpoints no IDE

### **Para Produção**
1. Use Kubernetes com Helm
2. Configure LoadBalancer
3. Adicione monitoring (Prometheus + Grafana)
4. Configure CI/CD (GitHub Actions, GitLab CI)

---

## 📞 Contato & Suporte

Se encontrar problemas na inicialização, verifique:

- [ ] Java 17+ instalado: `java -version`
- [ ] Maven funcionando: `mvn -v`
- [ ] Node.js instalado: `node -v`
- [ ] Portas 8081-8086 disponíveis: `netstat -ano` (Windows) ou `lsof -i` (Mac/Linux)
- [ ] Docker rodando (se usar Docker Compose)

**Avise se tiver dúvidas!** 🚀
