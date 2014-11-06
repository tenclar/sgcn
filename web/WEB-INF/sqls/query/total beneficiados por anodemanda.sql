#total de monitoramento por beneficiário
select
     c.anodemanda,
	  c.benstatus,   
 count(distinct c.id) as totalben	
from cidadao c
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
where c.tipopessoa = 'CID'
and c.benstatus = 'BENEFICIADO'
#and cid.estado_id =  1
GROUP BY
  c.anodemanda , c.benstatus
#with rollup
;


#total de monitoramento por beneficiário
select
     c.anodemanda,   
     cid.id as id_cidade,	
     cid.nome as nomecidade,
     # ramo.nome as ramo,
      #c.datacreated as datacadastro,

 count(distinct c.id) as totalben	
	  
from cidadao c

inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
#inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id

where c.tipopessoa = 'CID'
#and c.statuscid = 'INDIVIDUAL'
# and c.benstatus = 'INDIVIDUAL'
and c.benstatus <> 'CADASTRO'
#and not ISNULL(c.anodemanda)
#ND  YEAR(eq.dataentrega) = 2013
#and YEAR(c.datacreated) 
#and c.anodemanda = 2011
and cid.estado_id =  1

#and (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) >0

GROUP BY
  c.anodemanda 
  ,cid.nome
  #,ramo.nome

#ORDER BY
#      c.anodemanda,  cid.nome 
		#, ramo.nome 
with rollup
;