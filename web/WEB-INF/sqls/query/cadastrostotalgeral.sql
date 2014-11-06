select c.tipopessoa as tipopessoa, c.statuscid  as statuscid, c.benstatus as benstatus,
count(distinct c.id) as total
from cidadao c
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
WHERE cid.estado_id = 1
and c.benstatus = 'BENEFICIADO'
AND c.statuscid = 'COLETIVO'
group by c.tipopessoa,c.statuscid, c.benstatus 
order by c.tipopessoa desc, c.statuscid, c.benstatus