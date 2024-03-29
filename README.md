## 1. Spring Boot 3: desenvolva uma API Rest em Java
* Crie do zero uma API Rest em Java com Spring Boot;
* Desenvolva CRUDs utilizando o banco de dados MySQL;
* Utilize o Flyway como ferramenta de Migrations da API;
* Realize validações utilizando o Bean Validation;
* Realize paginação dos dados da API.

## 2. Spring Boot 3: aplique boas práticas e proteja uma API Rest
* Padronize os retornos dos controllers da API;
* Utilize os códigos HTTP corretamente;
* Adicione o módulo Spring Security no projeto;
* Implemente um mecanismo de autenticação na API;
* Realize o controle de acesso na API;
* Utilize Json Web Token para controlar o acesso na API.

## 3. Spring Boot 3: documente, teste e prepare uma API para o deploy
* Aprenda a isolar códigos de regras de negócio em uma aplicação;
* Implemente princípios SOLID;
* Documente uma API seguindo o padrão OpenAPI;
* Saiba como escrever testes automatizados em uma aplicação com Spring Boot;
* Realize o build de uma aplicação com Spring Boot;
* Utilize variáveis de ambiente e prepare uma aplicação para o deploy.
<br/>

## Conteúdos interessantes aprendidos neste curso:
* Criar validações e usar com o design pattern **strategy**;<br>
  https://github.com/ghmiyoshi/med-api/tree/main/src/main/java/br/com/alura/med/service/consulta/validacoes
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/service/consulta/ConsultaService.java

* Calcular a diferença de minutos entre duas datas;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/service/consulta/validacoes/ValidadorAntecedencia.java

* Uso do **JacksonTester** que converte uma classe em JSON para testes de controller;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/test/java/br/com/alura/med/controller/ConsultaControllerTest.java

* Tradução dos parâmetros de **pageable**;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/resources/application.yml

* Uso de **records** validando com anotações do **jakarta** e mensagens customizadas;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/domain/medico/DadosCadastroMedico.java
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/resources/ValidationMessages.properties

* Versionamento e gerenciamento do banco de dados com **Flyway**;<br>
  https://github.com/ghmiyoshi/med-api/tree/main/src/main/resources/db/migration

* Gerar documentação da API com **Swagger 3** e configurar o header **JWT**<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/config/doc/SpringDocConfig.java
<br/>

## Melhorias feitas:
* Adicionar auditoria e revisão usando **Spring Data Envers**;<br>
  https://github.com/ghmiyoshi/med-api/tree/main/src/main/java/br/com/alura/med/domain/audit

* Uso do **ProblemDetail** do Spring Boot 3 e switch do Java 17;<br>
  https://github.com/ghmiyoshi/med-api/blob/0a310b8601817c6e6b75cc67ef508b9ffdf4fd0b/src/main/java/br/com/alura/med/infra/handler/ApiExceptionHandler.java

* Configuração do Spring Security usando anotação **@PreAuthorize**;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/config/security/SecurityConfig.java

* Configuração do **jackson** para as propriedades snake case, não incluir nulos, etc;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/resources/application.yml

* Adicionar **cache** e configurar limpeza a cada uma hora com **scheduling**;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/config/SpringConfig.java
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/config/cache/CachingConfig.java

* Criar **Dockerfile** e **docker-compose.yml** com variaveis de ambiente **.env**;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/Dockerfile
  https://github.com/ghmiyoshi/med-api/blob/main/docker-compose.yml
  https://github.com/ghmiyoshi/med-api/blob/main/.env

* Configuração e utilização do **ObjectMapper** no toString() das models;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/domain/utils/ObjectMapperUtils.java
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/domain/JsonAbstract.java

* Utilização de **Spring Event** para disparar eventos;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/infra/event/EmailService.java
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/service/consulta/ConsultaService.java

* Criação de template ao abrir PRs e definição dos code owners do projeto;<br>
  https://github.com/ghmiyoshi/med-api/tree/main/.github

* Configuração CI com GitHub Actions.<br>
  https://github.com/ghmiyoshi/med-api/tree/main/.github/workflows
