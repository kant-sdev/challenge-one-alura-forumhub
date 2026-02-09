CREATE TABLE topicos (
     id BIGSERIAL PRIMARY KEY,

     titulo VARCHAR(255) NOT NULL,
     mensagem TEXT NOT NULL,

     data_criacao TIMESTAMP NOT NULL,

     autor_id UUID NOT NULL,

     tipo_forum VARCHAR(20) NOT NULL,
     curso VARCHAR(255),

     tipo_topico VARCHAR(20) NOT NULL,
     status VARCHAR(20),

     CONSTRAINT fk_topicos_autor
        FOREIGN KEY (autor_id)
            REFERENCES usuarios (id)
);
