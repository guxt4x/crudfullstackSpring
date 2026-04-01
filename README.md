# Backend Spring Boot

CRUD com MongoDB Atlas


## Atividade Prática

Consulte o enunciado e veja o diagrama das entidades abaixo para realizar a atividade proposta:

### Diagrama das Entidades

```mermaid
erDiagram
	CURSO {
		Long id
		String nome
		int cargaHoraria
		boolean ativo
	}
	PESSOA {
		Long id

		# Backend Spring Boot

		CRUD com MongoDB Atlas

		---

		## Atividade 1: CRUD Básico com Spring Boot

		### Enunciado

		Você está desenvolvendo uma aplicação de cadastro acadêmico utilizando Java, Spring Boot e JPA. O objetivo é praticar a criação de APIs REST completas, com operações básicas de cadastro (CRUD) para diferentes entidades do domínio escolar.

		#### O que você deve fazer:

		1. **Estude o exemplo das entidades `Curso` e `Pessoa` já implementadas no projeto.**
		   - Analise como estão organizados os arquivos Model, Repository, Service, Controller e DataLoader.
		   - Observe como cada camada se comunica e como as operações básicas (listar, criar, atualizar, excluir) são implementadas.

		2. **Crie mais 5 entidades seguindo exatamente o mesmo padrão:**
		   - Professor
		   - Disciplina
		   - Turma
		   - Matricula
		   - Avaliacao

		   Para cada entidade, implemente:
		   - Model (com atributos e anotações JPA)
		   - Repository (interface estendendo JpaRepository)
		   - Service (lógica de negócio, CRUD)
		   - Controller (endpoints REST)
		   - DataLoader (popular dados fake para testes)

		3. **Teste todos os endpoints utilizando o Postman ou outra ferramenta de sua preferência.**
		   - Garanta que é possível criar, listar, atualizar e excluir registros de cada entidade.

		4. **Documente no final do arquivo quais endpoints você criou e exemplos de uso.**

		#### Dicas:
		- Use nomes e tipos de atributos coerentes com o contexto de cada entidade.
		- Siga o padrão de organização do projeto para facilitar a manutenção e entendimento do código.
		- Não implemente pesquisa e paginação nesta branch (isso será feito em outra etapa).

		---

		### Diagrama das Entidades

		```mermaid
		erDiagram
			CURSO {
				Long id
				String nome
				int cargaHoraria
				boolean ativo
			}
			PESSOA {
				Long id
				String nome
				int idade
				String email
				boolean ativo
			}
			PROFESSOR {
				Long id
				String nome
				String especialidade
				String email
				boolean ativo
			}
			DISCIPLINA {
				Long id
				String nome
				int cargaHoraria
				boolean ativo
			}
			TURMA {
				Long id
				String nome
				int ano
				boolean ativo
			}
			MATRICULA {
				Long id
				Long pessoaId
				Long cursoId
				String dataMatricula
				boolean ativo
			}
			AVALIACAO {
				Long id
				Long pessoaId
				Long disciplinaId
				double nota
				String data
				boolean ativo
			}
    
			PESSOA ||--o{ MATRICULA : faz
			CURSO ||--o{ MATRICULA : possui
			PESSOA ||--o{ AVALIACAO : recebe
			DISCIPLINA ||--o{ AVALIACAO : compoe
			TURMA ||--o{ PESSOA : agrupa
			PROFESSOR ||--o{ DISCIPLINA : ministra
		```

		> O diagrama acima representa as entidades sugeridas para a atividade, com possíveis relações entre elas. Adapte conforme a evolução do seu projeto.

		---

		## Próximas Atividades

		### Atividade 2: Pesquisa e Paginação
		*Em breve: implemente endpoints de pesquisa e paginação para as entidades criadas, seguindo o padrão do Spring Data JPA.*

		### Atividade 3: Relacionamentos e Validações
		*Em breve: implemente relacionamentos entre entidades (ex: matrícula, avaliação) e regras de validação de dados.*