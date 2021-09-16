USE youcredit;
/* -------------------------------------------------------------- */
/*Criar Modelo PR-PRODUTO                                         */
/* -------------------------------------------------------------- */
DROP TABLE IF EXISTS pr_instituicao_credito; 
CREATE TABLE pr_instituicao_credito (
    instituicao_credito_id INT AUTO_INCREMENT,
    instituicao_credito VARCHAR(15) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    morada VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    nif VARCHAR(10) NOT NULL,
    contacto_comercial VARCHAR(50) NOT NULL,
    contacto_credito VARCHAR(50) NOT NULL,
    contacto_it VARCHAR(50) NOT NULL,
    protocolo_ref VARCHAR(30) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (instituicao_credito_id),
    CONSTRAINT pr_instituicaocredito_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS pr_estado_produto;
CREATE TABLE pr_estado_produto (
    estado_produto_id INT AUTO_INCREMENT,
    estado_produto VARCHAR(25) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (estado_produto_id),
    CONSTRAINT pr_estadoproduto_chk_estadoproduto CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS pr_produto;
CREATE TABLE pr_produto (
    produto_id INT AUTO_INCREMENT,
    produto VARCHAR(15) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    instituicao_credito_id INT NOT NULL,
    tempo_decisao INT NOT NULL, 
    limite_maximo DECIMAL(9,2) NOT NULL,
    limite_minimo DECIMAL(9,2) NOT NULL,
    taxa_juro DECIMAL(5,2) NOT NULL,
    taxa_imposto_contratacao DECIMAL(5,2) NOT NULL,
    taxa_despesa_contratacao DECIMAL(5,2) NOT NULL,
    taxa_imposto_cobranca DECIMAL(5,2) NOT NULL,
    taxa_despesa_cobranca DECIMAL(5,2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    estado_produto_id INT NOT NULL,
    data_estado_produto DATE, 
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (produto_id),
    FOREIGN KEY (instituicao_credito_id)
        REFERENCES pr_instituicao_credito (instituicao_credito_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (estado_produto_id)
        REFERENCES pr_estado_produto (estado_produto_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT pr_produto_chk_estadolog CHECK (estado_log="A" OR estado_log="I")    
);
DROP TABLE IF EXISTS pr_finalidade;
CREATE TABLE pr_finalidade (
    finalidade_id INT AUTO_INCREMENT,
    finalidade VARCHAR(20) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (finalidade_id),
    CONSTRAINT pr_finalidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")
);
DROP TABLE IF EXISTS pr_produto_finalidade;
CREATE TABLE pr_produto_finalidade (
    produto_finalidade_id INT AUTO_INCREMENT,
    produto_id INT NOT NULL,
    finalidade_id INT NOT NULL,
    descricao VARCHAR(50),    
    data_inicio DATE NOT NULL,
    data_fim DATE,
    data_log TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utilizador_log INT NOT NULL,
    estado_log VARCHAR(1) NOT NULL DEFAULT "A",
    PRIMARY KEY (produto_finalidade_id),
    FOREIGN KEY (produto_id)
        REFERENCES pr_produto (produto_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (finalidade_id)
        REFERENCES pr_finalidade (finalidade_id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT pr_produtofinalidade_chk_estadolog CHECK (estado_log="A" OR estado_log="I")    
);
