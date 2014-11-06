select cid.nome, 
IFNULL(ramo.nome,'TOTAL') as ramo, 
cast(sum(r.vendas - r.despesas) as decimal(10,2)) as lucro, 
count(distinct c.id) as monitoramentos, 
count(distinct r.datamon) as encubacoes, 
cast(avg(r.vendas-r.despesas) as decimal(10,2)) as media
 
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
inner join endereco e on e.id = c.endereco_id
INNER JOIN  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
#inner join estado es on es.id = cid.estado_id
INNER JOIN  mpm_rendimento r ON r.plano_id = p.id


where cid.estado_id = 1 
and c.tipopessoa = 'CID'
and c.benstatus = 'BENEFICIADO'
group by cid.nome
, ramo.nome 
with rollup
;