# Projeto de Testes Automatizados - API Restful

Este projeto implementa uma suíte de testes automatizados para validação de uma API RESTful utilizando Java, RestAssured, JUnit e Maven. A proposta é garantir a cobertura de funcionalidades essenciais da aplicação, identificar falhas, validar fluxos de exceção e sugerir melhorias.

## Tecnologias Utilizadas

- Java 17
- JUnit 5
- RestAssured
- Maven
- Allure
- IntelliJ IDEA / VSCode / Postman

---

## Como Executar os Testes

Clone o repositório e certifique-se de estar na branch `main`.

### 1. Via Maven direto:

Abrir o terminar no diretório e executar o comando:

<pre>mvn clean test -Dtest=br.rafae.suit.RegressionSuite</pre>

### 2. No projeto
Foi criado um pacote de suites. Dentro desse tipo de cenário há diversos tipos de suites que podem ser rodados, aqui foi abordado apenas 1 exemplo.


---

## Geração de relatórios
Para visualização do relatório no dash da Allure é necessário rodar o teste
conforme descrito acima. Após gerar os dados para o relatório via terminal:

<pre>allure generate allure-results --clean -o allure-report</pre>

E caso queira visualizar voce pode abrir allure-report/index.html direto
ou com o comando:

<pre>allure serve allure-results</pre>

## Plano de Testes e Estratégia

A estrutura de testes foi organizada para cobrir os seguintes pontos:

<li>Validação dos endpoints públicos (GET /products)</li>
<li> Validação de endpoints protegidos (GET /auth/products) com token de autenticação dinâmico</li>
<li>Fluxos negativos (payloads inválidos, autenticação ausente, erros esperados)</li>
<li> Cobertura de retornos HTTP esperados (200, 401, 403, 422, 500)</li>
<li> Reutilização de código por meio de utilitários (AuthUtils, BaseTest, constantes de URL)</li>
<li> Modularização por classes e suites</li>
<li> Verificação de campos importantes no corpo da resposta</li>


---

### Contribuição

Projeto desenvolvido por Rafael Teixeira como um exemplo para testes de API.

</br></br></br>


