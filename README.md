# Plano de Saúde API

Esta aplicação é um exemplo de API REST para gestão de beneficiários de um plano de saúde, desenvolvida com Spring Boot e PostgreSQL.

## Endpoints

- `POST /api/beneficiarios`: Cadastra um novo beneficiário.
- `GET /api/beneficiarios`: Lista todos os beneficiários.
- `GET /api/beneficiarios/{beneficiarioId}`: Obtém os detalhes de um beneficiário pelo ID.
- `PUT /api/beneficiarios/{beneficiarioId}`: Atualiza os dados de um beneficiário.
- `DELETE /api/beneficiarios/{id}`: Remove um beneficiário.
- `GET /api/documentos/beneficiario/{beneficiarioId}`: Lista todos os documentos de um beneficiário.

## Configuração

Este projeto foi implementado usando o IDE Eclipse.
Então você pode clonar o projeto e importá-lo facilmente para o Eclipse.

1. Clone o repositório.
2. Configure o banco de dados PostgreSQL em `src/main/resources/application.yaml`.

- O projeto original:
https://github.com/augustczar/healthforall


## Variáveis de ambiente
- Voce pode configurar suas variaveis no seu host, na sua IDE, ou em cloud.
- Facil configuração no eclipse com seu projeto selecionado:
	- Run - Run Configurations - Environment

	* AUTHORIZATION_HEADER=Authorization
	
	
	* JWT_SECRET=sua frase secreta  
	* OPEN_API_SERVER_URL=http://localhost:sua_porta/healthforall 
	* POSTGRE_PASSWORD=senha postgres  
	* POSTGRE_USERNAME=admin  
	* POSTGRE_URL=jdbc:postgresql://localhost:5432  
	* SERVER_PORT=sua porta

## Autenticação

 - Utilizar sua porta disponivel exemplo: 
	- port:8082  

 - Após subir o projeto - realizar o cadastros do usuário administrador:  
 - Cadastro usuário

  
 - endpoints:
	- http://localhost:port/healthforall/authuser/register
	  
 - massa administrador:
	- {
    "login": "seu login",
    "password": "sua senha",
    "role": "ADMIN_USER"
}  


- massa usuário:
	- {
    "login": "seu login",
    "password": "sua senha",
    "role": "COMMON_USER"
}  


 - Login: Retorna um token de configuração:
	- http://localhost:port/healthforall/authuser/login  

 - massa login do usuário:
	- {
    "login": "seu login",
    "password": "sua senha"
}  

## Documentação

 - http://localhost:port/healthforall/swagger-ui/index.html
 - Utilizar sua porta configurada na aplicação, exemplo: 
	- port:8080  

