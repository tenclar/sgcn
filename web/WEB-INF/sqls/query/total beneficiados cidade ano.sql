#total de monitoramento por beneficiário
select
    c.id,
    cid.id 'idcidade',
	 cid.nome as cidade,
	 ramo.id,
	 ramo.nome as ramo,
	 c.nome,
	 eq.dataentrega,  
	 c.datacreated,
	 c.benstatus,  
	 
    c.anodemanda	 
from cidadao c
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
left join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
where c.tipopessoa = 'CID'
#and c.statuscid = 'INDIVIDUAL'
#and c.statuscid <> 'COLETIVO'
# and c.benstatus = 'INDIVIDUAL'
and c.benstatus = 'BENEFICIADO'
#AND  YEAR(eq.dataentrega) = 2013
and  YEAR(c.datacreated) = 2014
#and c.anodemanda = 2013
and ISNULL(c.anodemanda) 
#and cid.id = 14
#and ramo.id = 1
and cid.estado_id = 1

GROUP BY
     
		 cid.nome, 
		 ramo.nome,
		 c.nome
order by cid.nome,ramo.nome, c.datacreated 
#eq.dataentrega		 
#with rollup
;


