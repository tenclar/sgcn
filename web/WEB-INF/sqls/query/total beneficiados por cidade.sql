#total de monitoramento por beneficiário
select

    #cid.id as id_cidade,
    
    c.anodemanda,
    	  cid.nome as cidade,
    #YEAR(c.datacreated) 'ano cadastro',
	  # eq.dataentrega,
     #ramo.nome as ramo,
	  count(distinct c.id) as totalben	 
	  #(SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequip
	
	  
from cidadao c

inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
#inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id

where c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'
# and c.benstatus = 'INDIVIDUAL'
and c.benstatus <> 'CADASTRO'
AND  YEAR(eq.dataentrega) = 2013
#and YEAR(c.datacreated)  = 2011
#and c.anodemanda = 2011
#and cid.id = 6
#and (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) >0

GROUP BY
       #YEAR(c.datacreated),
       c.anodemanda,
		  cid.nome 
		 #ramo.nome

#with rollup
ORDER BY
   c.anodemanda, cid.nome asc

;