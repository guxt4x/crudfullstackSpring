@echo off
REM Script para iniciar todos os microserviços no Windows
REM Cada serviço abrirá em uma nova janela do prompt

echo ========================================
echo Iniciando Microservicos...
echo ========================================
echo.

REM Compilar todos os serviços
echo [1/7] Compilando todos os serviços...
cd microservicos
call mvn clean install -DskipTests
echo.

REM Iniciar cada serviço em uma nova janela
echo [2/7] Iniciando Pessoa Service (porta 8082)...
start "Pessoa Service" cmd /k "cd pessoa-service && mvn spring-boot:run"
timeout /t 3 >nul

echo [3/7] Iniciando Matricula Service (porta 8081)...
start "Matricula Service" cmd /k "cd matricula-service && mvn spring-boot:run"
timeout /t 3 >nul

echo [4/7] Iniciando Disciplina Service (porta 8083)...
start "Disciplina Service" cmd /k "cd disciplina-service && mvn spring-boot:run"
timeout /t 3 >nul

echo [5/7] Iniciando Curso Service (porta 8084)...
start "Curso Service" cmd /k "cd curso-service && mvn spring-boot:run"
timeout /t 3 >nul

echo [6/7] Iniciando Turma Service (porta 8085)...
start "Turma Service" cmd /k "cd turma-service && mvn spring-boot:run"
timeout /t 3 >nul

echo [7/7] Iniciando Usuario Service (porta 8086)...
start "Usuario Service" cmd /k "cd usuario-service && mvn spring-boot:run"
timeout /t 3 >nul

echo.
echo ========================================
echo Todos os serviços iniciados!
echo ========================================
echo.
echo Serviços rodando:
echo  - Pessoa Service:    http://localhost:8082
echo  - Matricula Service: http://localhost:8081
echo  - Disciplina Service: http://localhost:8083
echo  - Curso Service:     http://localhost:8084
echo  - Turma Service:     http://localhost:8085
echo  - Usuario Service:   http://localhost:8086
echo.
echo Aguarde alguns segundos para todos os serviços ficarem prontos...
echo.
pause
