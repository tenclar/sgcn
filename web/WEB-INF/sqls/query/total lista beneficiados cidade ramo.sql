#total de monitoramento por beneficiário
select
	
    #cid.id as id_cidade,
	  cid.nome as nomecidade,
	  
    
    ramo.nome as ramo,
	  #count(distinct c.id) as totalben,
	  	  
	  c.id,
	   c.nome,
	   c.cpf,
c.datacreated as datacadastro,
 eq.dataentrega,
	 
	  c.anodemanda
	  #(SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequip
	
	  
from cidadao c

inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id

where c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'
# and c.benstatus = 'INDIVIDUAL'
and c.benstatus <> 'CADASTRO'
#and not ISNULL(c.anodemanda)
AND  YEAR(eq.dataentrega) = 2012
#and YEAR(c.datacreated)  = 2012
#and c.anodemanda = 2011
#and cid.id = 6
#and (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) >0

GROUP BY
      cid.nome, ramo.nome, c.nome

ORDER BY
       cid.nome asc, ramo.nome asc, c.datacreated desc

;