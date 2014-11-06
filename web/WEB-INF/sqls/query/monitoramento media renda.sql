select cid.nome, ramo.nome, sum(r.vendas - r.despesas) as lucro, count(r.datamon) as monitoramento, cast(avg(r.vendas-r.despesas) as decimal(10,2)) as media 
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
inner join endereco e on e.id = c.endereco_id
inner JOIN  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner JOIN  mpm_rendimento r ON r.plano_id = p.id


where cid.estado_id = 1
group by cid.nome, ramo.nome 
;