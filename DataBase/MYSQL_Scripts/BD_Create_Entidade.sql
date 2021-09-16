USE youcredit;
/* -------------------------------------------------------------- */
/*Criar Modelo EN-ENTIDADE                                        */
/* -------------------------------------------------------------- */
DROP TABLE IF EXISTS en_segmento_cliente;
CREATE TABLE en_segmento_cliente (
    segmento_cliente_id INT AUTO_INCREMENT,
    segmento_cliente VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (segmento_cliente_id),
    CONSTRAINT en_segmentocliente_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_tipo_relacao_entidade;
CREATE TABLE en_tipo_relacao_entidade (
    tipo_relacao_entidade_id INT AUTO_INCREMENT,
    tipo_relacao_entidade VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (tipo_relacao_entidade_id),
    CONSTRAINT en_tiporelacaoentidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_estado_entidade;
CREATE TABLE en_estado_entidade (
    estado_entidade_id INT AUTO_INCREMENT,
    estado_entidade VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (estado_entidade_id),
    CONSTRAINT en_estadoentidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_situacao_profissional;
CREATE TABLE en_situacao_profissional (
    situacao_profissional_id INT AUTO_INCREMENT,
    situacao_profissional VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (situacao_profissional_id),
    CONSTRAINT en_situacaoprofissional_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_sector_actividade;
CREATE TABLE en_sector_actividade (
    sector_actividade_id INT AUTO_INCREMENT,
    sector_actividade VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (sector_actividade_id),
    CONSTRAINT en_sectoractividade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_nacionalidade;
CREATE TABLE en_nacionalidade (
    nacionalidade_id INT AUTO_INCREMENT,
    nacionalidade VARCHAR(15) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (nacionalidade_id),
    CONSTRAINT en_nacionalidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS en_entidade;
CREATE TABLE en_entidade (
    entidade_id INT AUTO_INCREMENT,
    entidade VARCHAR(25) NOT NULL,
    nome VARCHAR(75) NOT NULL,
    nif VARCHAR(10),
    cartao_cidadao VARCHAR(15),
    passaporte VARCHAR(15), 
    morada VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) NOT NULL, 
    data_nascimento DATE NOT NULL,
    genero VARCHAR(10) NOT NULL,  
    entidade_empregadora VARCHAR(50),
    data_inicio_emprego DATE,
    rating INT NOT NULL, 
    salario_anual DECIMAL(9,2) NOT NULL,
    creditos_actuais DECIMAL(9,2) NOT NULL,
    lista_negra VARCHAR(75),
    sector_actividade_id INT NOT NULL,
    situacao_profissional_id INT NOT NULL,
    segmento_cliente_id INT NOT NULL,
    estado_entidade_id INT NOT NULL,
    nacionalidade_id INT NOT NULL, 
    data_estado_entidade DATE,
    existe_crm VARCHAR(1), 
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (entidade_id),
    FOREIGN KEY (sector_actividade_id)
        REFERENCES en_sector_actividade (sector_actividade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (situacao_profissional_id)
        REFERENCES en_situacao_profissional (situacao_profissional_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (estado_entidade_id)
        REFERENCES en_estado_entidade (estado_entidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (segmento_cliente_id)
        REFERENCES en_segmento_cliente (segmento_cliente_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (nacionalidade_id)
        REFERENCES en_nacionalidade (nacionalidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT en_entidade_chk_genero CHECK (genero="Masculino" OR genero="Feminino" OR genero="Neutro"),    
    CONSTRAINT en_entidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")    
);
DROP TABLE IF EXISTS en_relacao_entidades;
CREATE TABLE en_relacao_entidades (
    relacao_entidades_id INT AUTO_INCREMENT,
    entidade_pai_id INT NOT NULL,
    entidade_filho_id INT NOT NULL,
    tipo_relacao_entidade_id INT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (relacao_entidades_id),
    FOREIGN KEY (entidade_pai_id)
        REFERENCES en_entidade (entidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (entidade_filho_id)
        REFERENCES en_entidade (entidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (tipo_relacao_entidade_id)
        REFERENCES en_tipo_relacao_entidade (tipo_relacao_entidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT en_relacaoentidades_chk_estadolog CHECK (estado_log="A" OR estado_log="I")    
);
