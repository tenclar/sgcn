#select * from cidadao c where c.cpf = 334;

select mc.id,  c.nome, mc.associado_ids , c.id, c.statuscid , c.benstatus
from mci_cidassociados mc , cidadao c 
where c.cpf = mc.associado_ids 
and mc.cidadao_ids =532;