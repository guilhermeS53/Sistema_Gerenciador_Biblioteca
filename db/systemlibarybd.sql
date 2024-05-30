-- Cria a Tabela de Categorias
CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Cria a Tabela de Usuários
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
);

-- Cria a Tabela de Livros
CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    anoPub INT,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    emprestado BOOLEAN DEFAULT FALSE,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE SET NULL
);

-- Cria a Tabela de Empréstimos
CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    dataEmprestimo DATE NOT NULL,
    dataDevolucao DATE,
    status VARCHAR(255),
    multa DOUBLE PRECISION,
    usuario_id INTEGER,
    livro_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    FOREIGN KEY (livro_id) REFERENCES livro (id)
);

-- Criação do ENUM com ATIVO, DEVOLVIDO, ATRASADO
CREATE TYPE status_emprestimo AS ENUM ('ATIVO', 'DEVOLVIDO', 'ATRASADO');