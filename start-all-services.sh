#!/bin/bash

# Script para iniciar todos os microserviços em Linux/Mac
# Cada serviço rodará em um terminal separado (tmux) ou processo background

echo "========================================"
echo "Iniciando Microservicos..."
echo "========================================"
echo ""

cd microservicos

# Compilar todos os serviços
echo "[1/7] Compilando todos os serviços..."
mvn clean install -DskipTests
echo ""

# Função para iniciar um serviço
start_service() {
    local service_name=$1
    local port=$2
    echo "[*] Iniciando $service_name (porta $port)..."
    
    # Verificar se tmux está disponível
    if command -v tmux &> /dev/null; then
        tmux new-session -d -s "$service_name" -c "$service_name" "mvn spring-boot:run"
    else
        # Fallback para background process
        (cd "$service_name" && mvn spring-boot:run) &
    fi
    sleep 2
}

# Iniciar cada serviço
echo "[2/7] Iniciando Pessoa Service (porta 8082)..."
start_service "pessoa-service" "8082"

echo "[3/7] Iniciando Matricula Service (porta 8081)..."
start_service "matricula-service" "8081"

echo "[4/7] Iniciando Disciplina Service (porta 8083)..."
start_service "disciplina-service" "8083"

echo "[5/7] Iniciando Curso Service (porta 8084)..."
start_service "curso-service" "8084"

echo "[6/7] Iniciando Turma Service (porta 8085)..."
start_service "turma-service" "8085"

echo "[7/7] Iniciando Usuario Service (porta 8086)..."
start_service "usuario-service" "8086"

echo ""
echo "========================================"
echo "Todos os serviços iniciados!"
echo "========================================"
echo ""
echo "Serviços rodando:"
echo "  - Pessoa Service:     http://localhost:8082"
echo "  - Matricula Service:  http://localhost:8081"
echo "  - Disciplina Service: http://localhost:8083"
echo "  - Curso Service:      http://localhost:8084"
echo "  - Turma Service:      http://localhost:8085"
echo "  - Usuario Service:    http://localhost:8086"
echo ""
echo "Para ver os logs, use:"
echo "  tmux attach-session -t <service_name>"
echo ""
echo "Para parar todos os serviços:"
echo "  pkill -f 'mvn spring-boot:run'"
echo ""

# Se usando tmux, mostrar sessões
if command -v tmux &> /dev/null; then
    echo "Sessões ativas:"
    tmux list-sessions
fi
