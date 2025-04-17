CREATE TABLE tecnologia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    tipo VARCHAR(255)
);

CREATE TABLE idea (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    categoria VARCHAR(255),
    data_criacao TIMESTAMP
)

CREATE TABLE idea_tecnologia (
    idea_id BIGINT NOT NULL,
    tecnologia_id BIGINT NOT NULL,
    PRIMARY KEY (idea_id, tecnologia_id),
    FOREIGN KEY (idea_id) REFERENCES idea(id),
    FOREIGN KEY (tecnologia_id) REFERENCES tecnologia(id)
);