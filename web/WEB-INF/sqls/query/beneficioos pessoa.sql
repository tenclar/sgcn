select  coop.tipopessoa, coop.benstatus,coop.statuscid, count(coop.id) as total 
from cidadao coop
#where coop.tipopessoa = 'COOP'
group by coop.tipopessoa, coop.statuscid,coop.benstatus;

select c.tipopessoa, c.benstatus,c.statuscid, count(c.id) as total from cidadao c
inner JOIN  mci_cidassociados ass ON c.id = ass.associado_id
where c.tipopessoa= 'COOP' 
#AND c.statuscid = 'INDIVIDUAL'

group by c.tipopessoa,  c.statuscid,c.benstatus;