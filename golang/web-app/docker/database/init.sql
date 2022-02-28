CREATE TABLE produtos
(
    produto_id serial PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL UNIQUE,
    descricao  VARCHAR(255) NOT NULL,
    preco      DECIMAL      NOT NULL,
    quantidade INTEGER      NOT NULL
);

INSERT
INTO produtos(nome, descricao, preco, quantidade)
VALUES ('Camiseta', 'Preta', 19, 10),
       ('Fone', 'Muito bom', 99, 5);