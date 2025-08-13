# Protótipo Back-end: Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)

Este repositório contém o código-fonte do protótipo de back-end para o projeto SGHSS, desenvolvido como parte do Trabalho de Conclusão de Curso da disciplina de Projeto Multidisciplinar.

## Sobre o Projeto

O SGHSS é um sistema projetado para centralizar e gerenciar as operações da instituição de saúde VidaPlus. Este protótipo de back-end foca na implementação dos serviços essenciais, incluindo a gestão de pacientes, controle de acesso seguro e uma arquitetura robusta e escalável, servindo como a fundação para o sistema completo.

## Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL 8
* **Segurança:** Spring Security com Autenticação e Autorização via Tokens JWT
* **Validação:** Jakarta Bean Validation
* **Build Tool:** Apache Maven
* **Testes:** JUnit 5, Mockito, H2 Database (para testes de integração) e Postman (para testes de API).

## Pré-requisitos

Para executar este projeto localmente, você precisará ter as seguintes ferramentas instaladas:

* [JDK 17](https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8+](https://maven.apache.org/download.cgi)
* [MySQL Server 8](https://dev.mysql.com/downloads/installer/)
* [Postman](https://www.postman.com/downloads/)
* Uma IDE Java, como [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/)

## Configuração do Ambiente

Siga os passos abaixo para configurar o projeto na sua máquina.

## 1. Clone o Repositório:**

git clone https://github.com/EvandroJRa/sghss-backend.git

cd sghss-backend

## 2. Configure o Banco de Dados:

Abra o MySQL (via terminal ou MySQL Workbench).
Crie o banco de dados que a aplicação irá usar:
> CREATE DATABASE sghss_db;

## 3. Configure a Aplicação:
Navegue até o arquivo src/main/resources/application.properties.
Altere a senha do banco de dados para a senha que você configurou na sua instalação do MySQL.
> spring.datasource.password=SUA_SENHA_DO_MYSQL_AQUI

## Executando a Aplicação
Você pode executar a aplicação diretamente pela sua IDE.
 1- Abra o projeto no IntelliJ IDEA.
 2- Navegue até a classe src/main/java/com/sghss/SghssBackendApplication.java.
 3- Clique no ícone de "play" (▶️) ao lado do método main e selecione "Run".
 4- O servidor iniciará e estará escutando na porta 8080

## Executando os Testes
O projeto possui duas suítes de testes principais.
Testes Automatizados (Unitários e de Integração)
Você pode executar os testes automatizados que validam as camadas de serviço e repositório.
* Via IntelliJ: Navegue até a pasta src/test/java, clique com o botão direito sobre o pacote com.sghss e selecione "Run 'Tests in 'com.sghss''".
* Via Maven: No terminal, na raiz do projeto, execute o comando:
> mvn test    

## Testes da API (Manuais com Postman)
Para validar os fluxos de ponta a ponta, siga este roteiro.
1. Importe a Coleção:
 * Abra o Postman.
 * Clique em "Import" e selecione o arquivo SGHSS-TCC.postman_collection.json que está na raiz deste repositório.
2. Roteiro de Testes Essenciais:
 * Certifique-se de que a aplicação está rodando.
 * Execute as requisições que estão na collection na seguinte ordem:

    1- POST Cadastrar Novo Paciente: Cria um novo usuário paciente. 
        O corpo da requisição já está preenchido com dados de exemplo. 
        Clique em "Send" e espere uma resposta 201 Created.  

    2- POST Efetuar Login: Usa as credenciais do paciente recém-criado para se autenticar. 
        Clique em "Send" e espere uma resposta 200 OK com um token JWT no corpo. 
        Copie o valor do token.

      3- GET Buscar Paciente por ID: Testa o acesso a um recurso protegido.
          -Vá na aba "Authorization".
          -O tipo já deve estar como "Bearer Token".
          -Cole o token copiado do passo anterior no campo "Token". -
          -Clique em "Send" e espere uma resposta 200 OK com os dados do paciente.

Autor
 > Evandro J. Ramos

    RU: 1918027    