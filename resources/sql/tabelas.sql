CREATE TABLE aluno(
    id SERIAL NOT NULL,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    tipo_pessoa INTEGER,
    tel_fixo VARCHAR(17),
    tel_celular VARCHAR(17),
    data_nascimento DATE,
    cpf VARCHAR(14),
    rg VARCHAR(7),
    CONSTRAINT pk_aluno PRIMARY KEY(id)
);

ALTER TABLE aluno ALTER COLUMN tipo_pessoa SET DEFAULT 0;

CREATE TABLE endereco_aluno(
    id SERIAL NOT NULL,
    id_aluno INTEGER,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(200),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2),
    complemento TEXT,
    CONSTRAINT pk_endereco PRIMARY KEY(id)
);

ALTER TABLE endereco_aluno ADD CONSTRAINT fk_aluno 
FOREIGN KEY (id_aluno) REFERENCES aluno(id) ON DELETE CASCADE;

-- ==== PROFEFSSOR ====
CREATE TABLE professor(
    id SERIAL NOT NULL,
    tipo_pessoa INTEGER,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    rg VARCHAR(7),
    data_nascimento DATE,
    tipo_titulacao INTEGER,
    tel_fixo VARCHAR(17),
    tel_celular VARCHAR(17),
    observacao TEXT,
    data_cadastro DATE,
    data_alteracao DATE,
    CONSTRAINT pk_professor PRIMARY KEY(id)
);
ALTER TABLE professor ALTER COLUMN tipo_titulacao SET DEFAULT 0;
ALTER TABLE professor ALTER COLUMN tipo_pessoa SET DEFAULT 0;

CREATE TABLE endereco_professor(
    id SERIAL NOT NULL,
    id_professor INTEGER,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(200),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2),
    complemento TEXT,
    CONSTRAINT pk_endereco PRIMARY KEY(id)
);

ALTER TABLE endereco_professor ADD CONSTRAINT fk_professor 
FOREIGN KEY (id_professor) REFERENCES professor(id) ON DELETE CASCADE;

CREATE  OR REPLACE FUNCTION insert_aluno() RETURNS TRIGGER AS
$BODY$
    BEGIN
        IF NEW.nome IS NULL THEN
            RAISE EXCEPTION `Informe o nome do aluno.`;
        END IF;
        IF NEW.email IS NULL THEN
            RAISE EXCEPTION `Informe o email do aluno`;
        END IF;
        RETURN NEW;
    END;
$BODY$ LANGUAGE plpgsql;
 
CREATE TRIGGER insert_aluno_gatilho BEFORE INSERT OR UPDATE ON aluno
    FOR EACH ROW EXECUTE PROCEDURE insert_aluno();


