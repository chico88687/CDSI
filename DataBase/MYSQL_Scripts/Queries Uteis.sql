/* Definir base de dados por defeito */
use youcredit;

/* Verificar dados carregados via OpenXava para entidades*/  
select * from en_estado_entidade;
select * from en_sector_actividade;
select * from en_segmento_cliente;
select * from en_situacao_profissional;


select * from en_tipo_relacao_entidade;
select * from en_relacao_entidades;

/* Verificar dados carregados via OpenXava para produtos*/
select * from pr_instituicao_credito;
select * from pr_estado_produto;
select * from pr_produto;
select * from pr_finalidade;
select * from pr_produto_finalidade;

/* Exemplo Evocação StoreProcedure de forma manual*/
SET @@GLOBAL.event_scheduler = ON;
SET @TempoDecorrido = 10; 
CALL ActualizarEntidades(@TempoDecorrido,1,2); 
SELECT @TempoDecorrido;

/* Verificar que eventos existem definidos 
SHOW EVENTS; 

