use youcredit;

/*Visualizar entradas nas tabelas relacionadas a Simulacao*/
select * from estadosimulacao;
select * from simulacao;
SELECT estadoSimulacao_id FROM estadosimulacao WHERE estado = 'Para Aprovar';
SELECT estadoSimulacao_id FROM estadosimulacao WHERE estado = 'Registado';
SELECT nif FROM EN_Entidade WHERE entidade_id = '1';
SELECT estado FROM estadosimulacao WHERE estadoSimulacao_id = 2