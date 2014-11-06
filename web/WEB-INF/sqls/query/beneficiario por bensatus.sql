select c.tipopessoa,c.benstatus,c.statuscid, c.sexo, count(c.id) as total
from cidadao c
where c.tipopessoa = 'CID'
AND c.benstatus = 'BENEFICIADO'
and c.statuscid = 'INDIVIDUAL'
AND c.sexo = 'M'
group by c.tipopessoa, c.benstatus, c.statuscid,c.sexo
order by c.tipopessoa desc;

#select c.sexo, c.statuscid,c.benstatus, count(c.id) as total
#from cidadao c
#where c.tipopessoa = 'CID' AND
#c.benstatus <> 'CADASTRO'
#group by c.statuscid,c.benstatus, c.sexo
#order by c.tipopessoa desc
;

select c.sexo, count(c.id) as total
from cidadao c
where c.tipopessoa = 'CID'
AND c.benstatus <> 'CADASTRO'
group by c.sexo

with rollup
#order by c.tipopessoa desc
;