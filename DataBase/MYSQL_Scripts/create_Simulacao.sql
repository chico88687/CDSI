use youcredit;

drop table if exists Simulacao;
drop table if exists EstadoSimulacao;


create table EstadoSimulacao(
	estadoSimulacao_id int auto_increment,
    estado varchar(80),
    descricao varchar(500),
        
    primary key (estadoSimulacao_id)
	
);

create table Simulacao(
	simulacao_id INT AUTO_INCREMENT,
    simulacao varchar(25),
    codigo_produto int,
    codigo_entidade int,
    estadoSimulacao_id int,
    utilizador_criaRegisto int,
    utilizador_atualizaRegisto int,
    estado_registo varchar(50),
    data_criacaoRegisto Date,
    data_atualizacaoRegisto Date,
	data_inicio Date,
	data_fim Date,
	data_avaliacao Date,
	total_solicitado double,
	total_possivel double,
	duracao int,
	total_capitalContratado double,
	total_despesaContratada double,
	total_impostosContratados double,
	descricao_objetoCredito varchar(500),
	scoring int,
	data_alteracaoEstado timestamp,

	primary key (simulacao_id),

	FOREIGN KEY (codigo_produto)
		REFERENCES pr_produto (produto_id)
		ON UPDATE RESTRICT ON DELETE CASCADE,
	
    foreign key (estadoSimulacao_id)
		references EstadoSimulacao (estadoSimulacao_id)
		ON UPDATE RESTRICT ON DELETE CASCADE,
	
    foreign key (codigo_entidade)
		references en_entidade (entidade_id)
		ON UPDATE RESTRICT ON DELETE CASCADE
	

     
);