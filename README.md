# :book: Sistema Gerenciador de Biblioteca em Java

**Objetivo**: Implementar um sistema simples em Java que apresenta via linha de comando (CLI) um menu de Biblioteca com as opções:

<h1> ⚙ Funcionalidades Principais </h1>

:book: Livros
- Cadastrar Livro :white_check_mark:
- Listar Livros :white_check_mark:
- Buscar Livro (por Nome ou ID) :white_check_mark:
- Atualizar Livro :white_check_mark:
- Excluir Livro :white_check_mark:
- Emprestar Livro :white_check_mark:
- Devolver Livro :white_check_mark:

:bust_in_silhouette: Usuários
- Cadastrar Usuário :white_check_mark:
- Listar Usuários :white_check_mark:
- Atualizar Usuário :white_check_mark:
- Excluir Usuário :white_check_mark:

:palm_up_hand: Categoria
- Cadastrar Categoria :white_check_mark:
- Listar Categorias :white_check_mark:
- Atualizar Categoria :white_check_mark:
- Excluir Categoria :white_check_mark:

:palm_up_hand: Empréstimos
- Registrar Empréstimo :white_check_mark:
- Registrar Devolução :white_check_mark:
- Listar Empréstimos (através de Enumeradores sendo ATIVO, DEVOLVIDO ou ATRASADO) :white_check_mark:

<h1> ⚙ Precondições do Sistema  </h1>

- Implementar um sistema robusto com tratamento de exceções adequado
- Usar interações com um banco de dados para persistência de dados (JPA)
- Utilizar os conceitos de programação em camada

<h1> ⚙ Conceitos de Programação Aplicados ao Projeto  </h1>

- **Java**: Linguagem de programação orientada a objetos, robusta e amplamente utilizada para desenvolvimento de aplicações diversas.

- **Spring**: Framework que simplifica o desenvolvimento Java, oferecendo recursos para injeção de dependências, gerenciamento de transações e desenvolvimento web. 
Implementa o padrão MVC (Model-View-Controller), que separa a lógica de negócios (Model), a interface do usuário (View) e o fluxo de controle (Controller), tornando o código mais organizado e fácil de manter.
    - Arquivo pom.xml armazena todas as dependências, propriedades e configurações para um bom funcionamento no Spring.
- **Maven**: Gerenciador de projetos e pacotes que automatiza a construção, testes e implantação de aplicações Java, facilitando o controle de dependências.

- **JPA (Java Persistence API)**: Especificação que define uma interface padrão para mapeamento objeto-relacional, permitindo a persistência de dados em bancos de dados relacionais.

- **Hibernate**: Implementação popular da JPA, que facilita o mapeamento objeto-relacional e oferece recursos adicionais para otimização de consultas e cache.

- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional de código aberto, robusto e escalável, com recursos avançados como transações e consultas complexas.

- **UML** (Unified Modeling Language): Linguagem de modelagem visual utilizada para representar a estrutura, o comportamento e as interações de um sistema. No contexto deste projeto, UML é usado para criar diagramas de classes, que ilustram as classes, seus atributos, métodos e relacionamentos, facilitando a compreensão da arquitetura do sistema.


<h1> ⚙ Pré-requisitos do Ambiente  </h1>

- Java Development Kit (JDK)
- Gerenciador de Pacotes Maven
- Spring
- Maven
- Banco de Dados SQL (PostgreSQL)

<h1> Estrutura do Projeto com Maven </h1>

O Projeto de Biblioteca segue a estrutura padrão do Maven no padrão Spring:

<pre>
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── ifgoiano
│   │   │           └── biblioteca
│   │   │               └── controller
│   │   │               └── model
│   │   │               └── repository
│   │   │               └── service
│   │   │               └── util
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
├── .gitignore
├── pom.xml
└── README.md
</pre>

<div> 
  <h1>Tecnologias Utilizadas</h1>
<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,maven,vscode,postgres,hibernate" />
  </a>
</p>
 </div>