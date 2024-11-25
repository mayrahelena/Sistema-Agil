# Sistema Ágil - Projeto de Gestão para Mercearia

Este repositório contém o código para o desenvolvimento do **Sistema Ágil**, um sistema de gestão para a mercearia. O objetivo é fornecer um sistema para controlar caixa, vendas fiadas, estoque e gerar relatórios. O projeto é desenvolvido utilizando **Java** e **MySQL**.

## Estrutura do Projeto

A estrutura do projeto é organizada em pacotes, cada um com uma responsabilidade específica. Cada pacote contém as classes que implementam a lógica do sistema. Abaixo, está a descrição completa de cada pacote e suas respectivas classes.

### Pacote `com.sistemaagil.model`
Este pacote contém as classes que representam as entidades do sistema (as tabelas do banco de dados).

- **`Produto.java`**: Representa um produto da mercearia, com atributos como nome, preço, código e quantidade.
- **`Cliente.java`**: Representa um cliente da mercearia, com atributos como nome e histórico de compras.
- **`Venda.java`**: Representa uma venda, com os detalhes dos produtos comprados e o cliente que realizou a compra.
- **`Fiado.java`**: Representa uma venda fiada (a crédito), com detalhes de pagamento pendente e o cliente que comprou.
- **`Usuario.java`**: Representa um usuário do sistema (funcionário ou administrador), com login e senha.

### Pacote `com.sistemaagil.dao`
Este pacote contém as classes que realizam a conexão com o banco de dados e as operações de **CRUD** (criação, leitura, atualização e exclusão) das entidades.

- **`ProdutoDAO.java`**: Contém os métodos para acessar e manipular dados dos produtos no banco de dados.
- **`ClienteDAO.java`**: Contém os métodos para acessar e manipular dados dos clientes no banco de dados.
- **`VendaDAO.java`**: Contém os métodos para acessar e manipular dados das vendas realizadas.
- **`FiadoDAO.java`**: Contém os métodos para acessar e manipular dados de vendas fiadas.
- **`UsuarioDAO.java`**: Contém os métodos para acessar e manipular dados dos usuários do sistema.

### Pacote `com.sistemaagil.service`
Este pacote contém as classes que implementam a lógica de negócios do sistema, como regras de validação e cálculos.

- **`ProdutoService.java`**: Contém as regras de negócios para a manipulação de produtos (como adição, remoção e atualização de estoque).
- **`ClienteService.java`**: Contém as regras de negócios para o gerenciamento de clientes (como cadastro e atualização de informações).
- **`VendaService.java`**: Contém as regras de negócios para o processo de vendas (como realizar uma venda e calcular o total).
- **`FiadoService.java`**: Contém as regras de negócios para o controle de vendas fiadas (crédito) e pagamentos.
- **`UsuarioService.java`**: Contém as regras de negócios para o controle de usuários e autenticação no sistema.

### Pacote `com.sistemaagil.util`
Este pacote contém as classes utilitárias que são usadas em várias partes do sistema, como para gerenciar a conexão com o banco de dados.

- **`ConexaoDB.java`**: Classe responsável por estabelecer a conexão com o banco de dados MySQL.
- **`Validador.java`**: Classe com funções para validar informações, como CPF, datas e valores de pagamento.

### Pacote `com.sistemaagil.caixa`
Este pacote contém as classes que lidam com o controle de caixa da mercearia, como registrar entradas e saídas de valores.

- **`CaixaService.java`**: Contém as regras de negócios para registrar movimentações no caixa.
- **`CaixaDAO.java`**: Contém os métodos para manipular dados de movimentações de caixa no banco de dados.
- **`Pagamento.java`**: Representa um pagamento realizado no caixa, com métodos para calcular troco e tipos de pagamento.

### Pacote `com.sistemaagil.estoque`
Este pacote contém as classes que lidam com o controle de estoque da mercearia, como adicionar ou remover produtos.

- **`EstoqueService.java`**: Contém as regras de negócios para gerenciar o estoque.
- **`EstoqueDAO.java`**: Contém os métodos para manipular dados de produtos no estoque no banco de dados.

### Pacote `com.sistemaagil.fiado`
Este pacote contém as classes que lidam com o controle de vendas fiadas (a crédito), incluindo o histórico de pagamentos.

- **`FiadoService.java`**: Contém as regras de negócios para gerenciar vendas fiadas e pagamentos.
- **`FiadoDAO.java`**: Contém os métodos para manipular dados de vendas fiadas no banco de dados.

### Pacote `com.sistemaagil.login`
Este pacote contém as classes responsáveis pelo controle de autenticação de usuários.

- **`LoginService.java`**: Contém a lógica de negócios para autenticar um usuário (funcionário ou administrador).
- **`UsuarioDAO.java`**: Contém os métodos para acessar e manipular dados de usuários no banco de dados.

### Pacote `com.sistemaagil.relatorios`
Este pacote contém as classes responsáveis pela geração de relatórios.

- **`RelatorioService.java`**: Contém a lógica para gerar relatórios sobre vendas, estoque e fiado.
- **`RelatorioDAO.java`**: Contém os métodos para acessar dados e gerar os relatórios.

## Como Usar

### 1. Clonando o Projeto

Para começar a trabalhar no projeto, clone este repositório em sua máquina local usando o comando:
git clone https://github.com/mayrahelena/Sistema-Agil.git

### 2. Importando o Projeto

Após clonar o repositório, abra o **NetBeans** e siga os seguintes passos:

- Abra o NetBeans.
- Selecione **File** > **Open Project** (ou **Arquivo** > **Abrir Projeto**).
- Navegue até a pasta do projeto clonado e abra o projeto.

### 3. Configuração do Banco de Dados

1. **Configuração do Banco de Dados MySQL**:
   - Crie um banco de dados chamado `sistemaagil` no MySQL.
   - Adicione as tabelas necessárias conforme a estrutura do modelo (como `produtos`, `clientes`, `vendas`, etc.).
   - Atualize as credenciais no código para a conexão com o banco de dados, como usuário, senha, e host.

2. **Testando a Conexão**:
   - Após configurar o banco, inicie o projeto e teste se a conexão com o banco de dados foi estabelecida corretamente.

### 4. Divisão de Tarefas

Cada desenvolvedor deve se concentrar no pacote que lhe foi atribuído. As responsabilidades estão divididas da seguinte maneira:

- **Julio**: Trabalhar nos pacotes `com.sistemaagil.caixa` e nas classes `CaixaService`, `CaixaDAO`.
- **Douglas**: Trabalhar nos pacotes `com.sistemaagil.estoque` e nas classes `EstoqueService`, `EstoqueDAO`.
- **Mayra**: Trabalhar nos pacotes `com.sistemaagil.fiado` e `com.sistemaagil.login`, nas classes `FiadoService`, `LoginService`.
- **Felipe**: Trabalhar nos pacotes `com.sistemaagil.relatorios` e nas classes `RelatorioService`.

Cada desenvolvedor deve usar **branches** para suas funcionalidades e fazer commits frequentes.

## Como Contribuir

1. Crie uma branch para a sua funcionalidade, como `feature/caixa` ou `feature/estoque`.
2. Faça commits com mensagens claras sobre o que foi alterado.
3. Envie um **Pull Request** para revisão.
4. Após aprovação, o código será integrado ao projeto principal.