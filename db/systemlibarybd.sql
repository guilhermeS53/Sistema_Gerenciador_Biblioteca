-- Tabela de Categorias
CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela de Autores
CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
);

-- Tabela de Livros
CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    quantidade INTEGER,
    ano INTEGER,
    id_autor INTEGER,
    id_editora INTEGER,
    id_categoria INTEGER,
    FOREIGN KEY(id_autor) REFERENCES autor (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_editora) REFERENCES editora (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_categoria) REFERENCES categoria (id) ON DELETE CASCADE ON UPDATE CASCADE,
);

-- Tabela de Empréstimos
CREATE DOMAIN devolucao AS VARCHAR CHECK (VALUE = 'Devolvido' OR VALUE = 'Pendente');

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_prev_devolucao DATE GENERATED ALWAYS AS 
    (data_emprestimo + INTERVAL '15 days') STORED,
    data_real_devolucao DATE,
    devolucao VARCHAR(11),
    multa INTEGER DEFAULT 0,
    usuario_id INTEGER,
    livro_id INTEGER,
    FOREIGN KEY(usuario_id) REFERENCES usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(livro_id) REFERENCES livro (id) ON DELETE CASCADE ON UPDATE CASCADE	
);

-- Adição da chave estrangeira de avaliações 
ALTER TABLE avaliacao
ADD FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE;