select 
     cid.nome as cidade,
   #coop.id,coop.cpf,
	  # coop.benstatus as bencoop,
    # coop.nome, ass.associado_id, coop.benstatus as bencoop,e.id, e.logradouro, b.nome, cid.nome,
	  #c.nome, c.id as cidadaos, c.statuscid, c.benstatus, c.tipopessoa, ec.logradouro, bc.nome, cidc.nome
count(DISTINCT ass.associado_id) as totalben,
count(distinct coop.id) as totalcoop,
IFNULL(sum(distinct eq.valor),0)  as totalinvest
from cidadao coop
left  join mci_cidassociados ass on ass.cidadao_id = coop.id
left join mci_equipamentossecretaria eq on eq.cidadao_id = coop.id

inner join endereco e on e.id = coop.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id

#inner join endereco ec on ec.id = c.endereco_id
#inner join bairro bc on bc.id = ec.bairro_id
#inner join cidade cidc on cidc.id = bc.cidade_id

where coop.benstatus = 'BENEFICIADO'
and coop.tipopessoa = 'COOP'
#and cidc.estado_id <> 1

#AND c.tipopessoa = 'CID'
#and c.benstatus = 'BENEFICIADO'
#AND c.statuscid  = 'INDIVIDUAL'

GROUP BY   cid.nome
with ROLLUP

;

-- select coop.id,   coop.nome,c.nome, coop.benstatus as bencoop from cidadao coop
-- inner  join mci_cidassociados ass on ass.cidadao_id = coop.id
-- inner JOIN cidadao c on c.id = ass.associado_id
-- where coop.tipopessoa = 'COOP'
-- and coop.benstatus = 'BENEFICIADO'
-- group by coop.nome, c.nome
-- ;
-- 
-- select distinct count(ass.associado_id) from mci_cidassociados ass , cidadao c
-- inner join endereco e on e.id = c.endereco_id
-- inner join bairro b on b.id = e.bairro_id
-- inner join cidade cid on cid.id = b.cidade_id
-- WHERE cid.estado_id = 1
-- AND c.id= ass.associado_id
-- and c.benstatus = 'BENEFICIADO'
