-- Criação do ENUM de roles
CREATE TYPE user_role AS ENUM (
    'ROLE_ALUNO',
    'ROLE_INSTRUTOR',
    'ROLE_ADMIN'
);

-- Criação da tabela de usuários
CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    senha VARCHAR(255) NOT NULL,
    role user_role NOT NULL DEFAULT 'ROLE_ALUNO',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Índice adicional para autenticação (boa prática)
CREATE INDEX idx_usuarios_email ON usuarios(email);
