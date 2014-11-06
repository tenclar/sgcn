#total de monitoramento por beneficiário
select
     
     c.anodemanda AS DEMANDA,
     cid.nome AS CIDADES,
	    cop.nome AS COOPERATIVA,
		 ramo.nome as RAMOEMPREENDIMENTO, 
 count(DISTINCT c.id) as TOTALASSOC,
 count(DISTINCT ramo.id) as TOTALEMPR,
 count(distinct cop.id) as TOTALCOOP	
from cidadao cop
inner join mci_cidassociados assoc on assoc.cidadao_id = cop.id
inner join cidadao c on c.id = assoc.associado_id
inner join mci_ramoempreendimento ramo on ramo.id = c.ramoempreendimento_id
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id

where cop.tipopessoa = 'COOP'

#and c.benstatus = 'BENEFICIADO'
#and cid.estado_id =  1
GROUP BY
   c.anodemanda , cid.nome,  cop.nome ,ramo.nome
WITH ROLLUP
;