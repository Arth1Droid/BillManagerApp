# Gerenciador de Contas Residenciais

## 📌 Sobre o Projeto
Este projeto é uma aplicação **Java Spring Boot** desenvolvida para o **gerenciamento de contas residenciais**, como **água, luz, internet, telefone**, entre outras.  
O objetivo é permitir o controle organizado dessas despesas, facilitando o acompanhamento de valores, datas de vencimento e status de pagamento.

A aplicação foi desenvolvida com foco em boas práticas de back-end, utilizando **Spring Boot** e **PostgreSQL**, simulando um cenário real de uso para aprendizado e portfólio.

---

## 🚀 Funcionalidades
- Cadastro de contas (água, luz, internet, etc.)
- Edição de informações das contas
- Exclusão de contas
- Listagem de todas as contas cadastradas
- Controle de valor, data de vencimento e status de pagamento

---

## 🛠️ Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Hibernate**

---

## 🗄️ Banco de Dados
O projeto utiliza o **PostgreSQL** como sistema de banco de dados relacional, integrado ao Spring Boot via **JPA/Hibernate** para mapeamento objeto-relacional (ORM).

---

## ⚙️ Configuração do Projeto

### Pré-requisitos
- Java 17 (ou compatível com o projeto)
- Maven
- PostgreSQL

### Configuração do banco de dados
No arquivo `application.properties` (ou `application.yml`), configure a conexão com o banco:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador_contas
spring.datasource.username=postgres
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update

```
## ▶️ Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
2. Configure o banco de dados PostgreSQL
3. Ajuste o arquivo application.properties com as credenciais do banco
4. Execute o projeto:
   ```bash
   mvn spring-boot:run

---

## 📚 Objetivo Acadêmico
Este projeto foi desenvolvido com fins **educacionais**, com o objetivo de consolidar conhecimentos em:
- Desenvolvimento back-end com Java
- Arquitetura REST
- Persistência de dados com JPA
- Integração com banco de dados relacional

---

## 👤 Autor
**ArthDroid1**  
Estudante de Análise e Desenvolvimento de Sistemas

---

## 📄 Licença
Este projeto é de uso livre para fins de estudo e aprendizado.


  













