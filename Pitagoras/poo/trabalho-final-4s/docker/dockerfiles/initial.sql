CREATE TABLE isto_e_aquilo.usuarios
(
    usuario_id INT AUTO_INCREMENT
        PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    senha      VARCHAR(16)  NOT NULL,
    login      VARCHAR(20)  NOT NULL,
    permissao  VARCHAR(50)  NULL,
    CONSTRAINT login
        UNIQUE (login)
);

INSERT INTO usuarios (nome, senha, login, permissao)
VALUES ('Test User', '123456', 'root', 'ADM');

