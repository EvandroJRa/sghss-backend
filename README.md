# Protótipo Back-end: Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)

Este repositório contém o código-fonte do protótipo de back-end para o projeto SGHSS, desenvolvido como parte do Trabalho de Conclusão de Curso da disciplina de Projeto Multidisciplinar.

## Sobre o Projeto

O SGHSS é um sistema projetado para centralizar e gerênciar as operações da instituição de saúde VidaPlus. Este protótipo de back-end foca na implementação dos serviços essenciais, incluindo a gestão de pacientes, controle de acesso seguro e uma arquitetura robusta e escalável, servindo como a fundação para o sistema completo.

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

* JDK 17
* Maven 3.8+
* MySQL Server 8
* Postman
* Uma IDE Java, como o IntelliJ IDEA

## Configuração do Ambiente

Siga os passos abaixo para configurar o projeto na sua máquina.

**1. Clone o Repositório:**
```bash 
git clone [https://github.com/EvandroJRa/sghss-backend.git](https://github.com/EvandroJRa/sghss-backend.git)
cd sghss-backend 
```
****************************************************************
**2.Configure o Banco de Dados:**

2.1 Abra o MySQL (via terminal ou MySQL Workbench).
2.2 Crie o banco de dados que a aplicação irá usar:
> CREATE DATABASE sghss_db;
****************************************************************
**3.Configure a Aplicação:**

3.1 Navegue até o arquivo src/main/resources/application.properties.

3.2 Altere a senha do banco de dados para a senha que você configurou na sua instalação do MySQL.
> spring.datasource.password=SUA_SENHA_DO_MYSQL_AQUI
****************************************************************
**4.Executando a Aplicação**

-Você pode executar a aplicação diretamente pela sua IDE.

 4.1 Abra o projeto no IntelliJ IDEA.

 4.2 Navegue até a classe src/main/java/com/sghss/SghssBackendApplication.java.

 4.3 Clique no ícone de "play" (▶️) ao lado do método main e selecione "Run".
     O servidor iniciará e estará escutando na porta 8080.
****************************************************************
**5.Executando os Testes**
O projeto possui duas suítes de testes principais.
Testes Automatizados (Unitários e de Integração)
Você pode executar os testes automatizados que validam as camadas de serviço e repositório.

5.1 Via IntelliJ: Navegue até a pasta src/test/java, clique com o botão direito sobre o pacote com.sghss e selecione "Run 'Tests in 'com.sghss''".

5.2 Via Maven: No terminal, na raiz do projeto, execute o comando:
> mvn test
****************************************************************
**6.Testes da API (Manuais com Postman)**
Para validar os fluxos de ponta a ponta, siga este roteiro.

 6.1 Importe a Coleção:

 6.2 Abra o Postman.

 6.3 Clique em "Import" e selecione o arquivo SGHSS-TCC.postman_collection.
 json que está na raiz deste repositório.
****************************************************************
***7.Roteiro de Testes Essenciais:***

Certifique-se de que a aplicação está rodando.
O roteiro a seguir valida o fluxo completo de cadastro, 
a autenticação de diferentes perfis e as regras de autorização granular. 

-- Execute as requisições na seguinte ordem:

7.1 POST Cadastrar Novo Paciente: 

    Cria um novo usuário Paciente. 

O corpo da requisição já está preenchido com dados de exemplo.

    Resultado Esperado: 201 Created. Anote o ID retornado (ex: 1).

7.2 POST Cadastrar administrador: Cria um novo usuário Administrador.

    Resultado Esperado: 200 OK.

7.3 POST Efetuar Login (Paciente): Usa as credenciais do Paciente criado para se autenticar.

    Resultado Esperado: 200 OK com um token JWT. Copie o token do Paciente.

7.4 POST Efetuar Login ADM: Usa as credenciais do Administrador.

    Resultado Esperado: 200 OK com um token JWT. Copie o token do Admin.

7.5 GET Buscar Paciente por ID (Sucesso - Admin): Tente buscar os dados do Paciente (ID 1) usando o token do Admin.
    
    Resultado Esperado: 200 OK. (Prova que o Admin pode ver os dados de outros).

7.6 GET Buscar Paciente por ID (Sucesso - Dono do Dado): 

Tente buscar os dados do Paciente (ID 1) usando o token do próprio Paciente.

    Resultado Esperado: 200 OK. (Prova que um usuário pode ver seus próprios dados).

7.7 GET TSE-001 (Falha de Autorização - Perfil Incorreto): 

Tente acessar o endpoint de admin GET /api/admin/dashboard usando o token do Paciente.

    Resultado Esperado: 403 Forbidden. (Prova que um Paciente não pode acessar recursos de Admin).

**AUTOR : Evandro J. Ramos, RU 1918027**