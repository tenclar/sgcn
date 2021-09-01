select c.id, c.nome as nome, c.cpf as cpf, ra.nome as ramo, e.logradouro as endereco, cid.nome as cidade, b.nome as bairro, c.numero, IFNULL(count(distinct eq.id),0) as totalequip
from cidadao c
left join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
#left join regional rg on rg.id = b.regional_id
inner join cidade cid on cid.id = b.cidade_id
inner join  mci_ramoempreendimento ra ON ra.id = c.ramoempreendimento_id
inner join mci_publico pu on pu.id = c.publico_id
inner join estadocivil es on es.id = c.estadocivil_id

where c.tipopessoa = "CID"


and c.benstatus LIKE '%%'
and c.statuscid LIKE '%%'
and cid.id LIKE '%%'
and b.id LIKE '%%'
and ra.id LIKE '%%'
and pu.id like '%%'
and es.id like '%%'
and c.sexo like '%%'
and eq.convenio_id like '%%'
AND cid.estado_id = 1
and anodemanda BETWEEN 2011 AND  2018

group by cid.nome, c.nome
order by cid.nome asc, c.nome asc