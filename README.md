# med-api

Conteúdos interessantes apresentados neste curso:

* Criar validações e usar com o design pattern **strategy**;<br>
  https://github.com/ghmiyoshi/med-api/tree/main/src/main/java/br/com/alura/med/service/consulta/validacoes

* Calcular a diferença de minutos entre duas datas;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/service/consulta/validacoes/ValidadorAntecedencia.java

* Uso do JacksonTester que converte uma classe em JSON para testes de controller;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/test/java/br/com/alura/med/controller/ConsultaControllerTest.java

* Tradução dos parâmetros de pageable;<br>
  https://github.com/ghmiyoshi/med-api/blob/main/src/main/resources/application.yml
  
 * Uso de records validando com anotações do jakarta <br>
https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/domain/medico/DadosCadastroMedico.java

Melhorias feitas:
* Adicionar auditoria e revisão usando Spring Data Envers;<br>
https://github.com/ghmiyoshi/med-api/tree/main/src/main/java/br/com/alura/med/domain/audit

* Uso do ProblemDetail do Spring Boot 3 e switch do Java 17;<br>
https://github.com/ghmiyoshi/med-api/blob/0a310b8601817c6e6b75cc67ef508b9ffdf4fd0b/src/main/java/br/com/alura/med/infra/handler/ApiExceptionHandler.java

* Configuração do Spring Security usando anotação @PreAuthorize;<br>
https://github.com/ghmiyoshi/med-api/blob/main/src/main/java/br/com/alura/med/infra/security/SecurityConfigurations.java

* Configuração do jackson para as propriedades snake_case, não incluir nulos, etc <br>
https://github.com/ghmiyoshi/med-api/blob/main/src/main/resources/application.yml
