CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE editora (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE resenha (
    id SERIAL PRIMARY KEY,
    texto TEXT,
    nota INTEGER
);

CREATE DOMAIN cpf AS VARCHAR(11) CHECK (VALUE ~ '^[0-9]{11}$');

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    quantidade_emprestimos INTEGER DEFAULT 0
);

CREATE DOMAIN nota AS INTEGER CHECK (VALUE >= 0 AND VALUE <= 5);

CREATE TABLE avaliacao (
    id SERIAL PRIMARY KEY,
    comentario VARCHAR(255),
    nota INTEGER CHECK (nota >= 0 AND nota <= 5),
    usuario_id INTEGER,	
    livro_id INTEGER,
    FOREIGN KEY(usuario_id) REFERENCES usuario (id) ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    quantidade INT,
    ano INT,
    id_autor INTEGER,
    id_editora INTEGER,
    id_categoria INTEGER,
    id_resenha INTEGER,
    FOREIGN KEY(id_autor) REFERENCES autor (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_editora) REFERENCES editora (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_categoria) REFERENCES categoria (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_resenha) REFERENCES resenha (id) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE avaliacao
ADD FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE;

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
