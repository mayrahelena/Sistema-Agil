Sistema Ágil - Projeto de Gestão para Mercearia
Este repositório contém o código para o desenvolvimento do Sistema Ágil , um sistema de gestão para a mercearia. O objetivo é fornecer um sistema para controlar caixa, vendas fiadas, estoque e gerar relatórios. O projeto é desenvolvido utilizando Java e MySQL .

Estrutura do Projeto
A estrutura do projeto é organizada em pacotes, cada um com uma responsabilidade específica. Cada pacote contém as classes que implementam a lógica do sistema. Abaixo está a descrição completa de cada pacote e suas respectivas classes.

Pacotecom.sistemaagil.model
Este pacote contém as classes que representam as entidades do sistema (as tabelas do banco de dados).

Produto.java: Representa um produto da mercearia, com atributos como nome, preço, código e quantidade.
Cliente.java: Representa um cliente da mercearia, com atributos como nome e histórico de compras.
Venda.java: Representa uma venda, com os detalhes dos produtos comprados e o cliente que realizou uma compra.
Fiado.java: Representa uma venda fiada (a crédito), com detalhes de pagamento pendente e o cliente que comprou.
Usuario.java: Representa um usuário do sistema (funcionário ou administrador), com login e senha.
Pacotecom.sistemaagil.dao
Este pacote contém as classes que realizam a conexão com o banco de dados e as operações de CRUD (criação, leitura, atualização e exclusão) das entidades.

ProdutoDAO.java: Contém os métodos para acessar e manipular dados dos produtos no banco de dados.
ClienteDAO.java: Contém os métodos para acessar e manipular dados dos clientes no banco de dados.
VendaDAO.java: Contém os métodos para acessar e manipular dados das vendas realizadas.
FiadoDAO.java: Contém os métodos para acessar e manipular dados de vendas fiadas.
UsuarioDAO.java: Contém os métodos para acessar e manipular dados dos usuários do sistema.
Pacotecom.sistemaagil.service
Este pacote contém as classes que implementam a lógica de negócios do sistema, como regras de validação e cálculos.

ProdutoService.java: Contém as regras de negócios para a manipulação de produtos (como adição, remoção e atualização de estoque).
ClienteService.java: Contém as regras de negócios para o gerenciamento de clientes (como cadastro e atualização de informações).
VendaService.java: Contém as regras de negócios para o processo de vendas (como realizar uma venda e calcular o total).
FiadoService.java: Contém as regras de negócios para o controle de vendas fiadas (crédito) e pagamentos.
UsuarioService.java: Contém as regras de negócios para o controle de usuários e autenticação no sistema.
Pacotecom.sistemaagil.util
Este pacote contém classes utilitárias que são usadas em várias partes do sistema, como para gerenciar a conexão com o banco de dados.

ConexaoDB.java: Classe responsável por estabelecer a conexão com o banco de dados MySQL.
Validador.java: Classe com funções para validar informações, como CPF, dados e valores de pagamento.
Pacotecom.sistemaagil.caixa
Este pacote contém as classes que lidam com o controle de caixa da mercearia, como registrador de entradas e saídas de valores.

CaixaService.java: Contém as regras de negócios para movimentações de registradores na caixa.
CaixaDAO.java: Contém os métodos para manipular dados de movimentações de caixa no banco de dados.
Pagamento.java: Representa um pagamento realizado na caixa, com métodos para calcular troco e tipos de pagamento.
Pacotecom.sistemaagil.estoque
Este pacote contém as classes que lidam com o controle de estoque da mercearia, como adicionar ou remover produtos.

EstoqueService.java: Contém as regras de negócios para gerenciamento do estoque.
EstoqueDAO.java: Contém os métodos para manipular dados de produtos no estoque no banco de dados.
Pacotecom.sistemaagil.fiado
Este pacote contém as classes que lidam com o controle de vendas fiadas (a crédito), incluindo o histórico de pagamentos.

FiadoService.java: Contém as regras de negócios para gerenciamento de vendas fiadas e pagamentos.
FiadoDAO.java: Contém os métodos para manipular dados de vendas fiadas no banco de dados.
Pacotecom.sistemaagil.login
Este pacote contém as classes responsáveis ​​pelo controle de autenticação dos usuários.

LoginService.java: Contém a lógica de negócios para autenticar um usuário (funcionário ou administrador).
UsuarioDAO.java: Contém os métodos para acessar e manipular dados de usuários no banco de dados.
Pacotecom.sistemaagil.relatorios
Este pacote contém as classes responsáveis ​​pela geração de relatórios.

RelatorioService.java: Contém a lógica para gerar relatórios sobre vendas, estoque e fiado.
RelatorioDAO.java: Contém os métodos para acessar dados e gerar os relatórios.
Como usar
1. Clonando o Projeto
Para começar a trabalhar no projeto, clone este repositório em sua máquina local usando o comando: git clone https://github.com/mayrahelena/Sistema-Agil.git

2. Importando o Projeto
Após clonar o repositório, abra o NetBeans e siga os seguintes passos:

Abra o NetBeans.
Selecione Arquivo > Abrir Projeto (ou Arquivo > Abrir Projeto ).
Navegue até a pasta do projeto clonado e abra o projeto.
3. Configuração do Banco de Dados
Configuração do Banco de Dados MySQL :

Crie um banco de dados chamado sistemaagilno MySQL.
Adicione as tabelas que permitem conforme a estrutura do modelo (como produtos, clientes, vendas, etc.).
Atualize as credenciais no código para a conexão com o banco de dados, como usuário, senha, e host.
Testando a Conexão :

Após configurar o banco, inicie o projeto e teste se a conexão com o banco de dados foi estabelecida corretamente.
4. Divisão de Tarefas
Cada desenvolvedor deve se concentrar no pacote que lhe foi atribuído. As responsabilidades divididas são da seguinte maneira:

Julio : Trabalhar nos pacotes com.sistemaagil.caixae nas aulas CaixaService, CaixaDAO.
Douglas : Trabalhar nos pacotes com.sistemaagil.estoquee nas aulas EstoqueService, EstoqueDAO.
Mayra : Trabalhar nos pacotes com.sistemaagil.fiadoe com.sistemaagil.login, nas aulas FiadoService, LoginService.
Felipe : Trabalhar nos pacotes com.sistemaagil.relatoriose nas aulas RelatorioService.
Cada desenvolvedor deve usar ramificações para suas funcionalidades e fazer commits frequentes.

Como Contribuir
Crie um branch para sua funcionalidade, como feature/caixaou feature/estoque.
Faça commits com mensagens claras sobre o que foi alterado.
Envie um Pull Request para revisão.
Após aprovação, o código será integrado ao projeto principal.
