#total de monitoramento por beneficiário
select
     cid.nome as cidade,
     ramo.nome as ramo,
	  count(distinct c.id) as totalben
from cidadao c
inner join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id

where c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'
and c.benstatus = 'INDIVIDUAL'
AND  YEAR(eq.dataentrega) = 2012
and (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) >0

GROUP BY
      cid.nome, ramo.nome

ORDER BY
       cid.nome asc, ramo.nome asc

;