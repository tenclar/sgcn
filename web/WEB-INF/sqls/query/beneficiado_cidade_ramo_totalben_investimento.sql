#total de monitoramento por beneficiário
select
     cid.nome as cidade,
     ramo.nome as ramo,
count(distinct c.id) as totalben,
sum(distinct eq.valor) as totalinvest
from cidadao c

inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
left join mci_equipamentossecretaria eq on eq.cidadao_id = c.id

where c.tipopessoa = 'CID'
and c.benstatus = 'BENEFICIADO'
and c.statuscid = 'INDIVIDUAL'
and cid.estado_id =1 

GROUP BY
      cid.nome, ramo.nome

with ROLLUP

#ORDER BY
 #      cid.nome asc, ramo.nome asc

;