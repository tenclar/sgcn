
select
     coop.anodemanda,
     cid.nome as cidade,
     
     ramo.nome as ramo,
	  count(distinct ass.associado_id) as totalben,
sum(eq.valor) as totalinvest
from cidadao coop
inner join mci_cidassociados ass on ass.cidadao_id = coop.id
inner join mci_equipamentossecretaria eq on eq.cidadao_id = coop.id
inner join endereco e on e.id = coop.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join  mci_ramoempreendimento ramo ON ramo.id = coop.ramoempreendimento_id

where  coop.benstatus = 'BENEFICIADO'
#and c.statuscid = 'INDIVIDUAL'
AND coop.tipopessoa = 'COOP'

GROUP BY
      coop.anodemanda, cid.nome, ramo.nome

with ROLLUP
#ORDER BY
 #      coop.anodemanda desc, cid.nome asc, ramo.nome asc

;

#select count(c.id) as total
#from cidadao c
#WHERE 
#c.tipopessoa = 'CID'
#AND c.benstatus <> 'CADASTRO';

#select c.sexo, count(c.id) as total, (select sum(e.valor) from mci_equipamentossecretaria e where e.cidadao_id = c.id ) 'valor investido' 
#from cidadao c
#WHERE c.tipopessoa = 'CID'
#AND c.benstatus = 'BENEFICIADO'
#group by c.sexo;

#select c.tipopessoa,c.statuscid,c.benstatus, count(c.id) as total
#select c.sexo, c.benstatus, c.statuscid, count(c.id) as total
#from cidadao c
#WHERE 
#c.tipopessoa = 'CID'
# AND c.benstatus <> 'CADASTRO'

#group by c.sexo, c.benstatus
#order by c.tipopessoa desc;



