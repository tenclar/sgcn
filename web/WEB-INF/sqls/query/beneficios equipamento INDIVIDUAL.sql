select c.id, c.nome, c.cpf as CNPJ, c.benstatus AS StatusBeneficio, remp.nome ,
(select count(e.cidadao_id) from mci_equipamentossecretaria e where e.cidadao_id = c.id) as total_Equipamentos  
#(select count(ass.cidadao_id) from mci_cidassociados ass where ass.cidadao_id = c.id) as total_Associados  
from cidadao c, mci_ramoempreendimento remp
#left JOIN  mci_equipamentossecretaria e ON c.id = e.cidadao_id
#inner join mci_cidassociados ass on c.id = ass.cidadao_id
#inner JOIN  cidadao cd ON cd.id = ciass.associado_id
where c.tipopessoa= 'CID'
and c.ramoempreendimento_id = remp.id
and c.ramoempreendimento_id  in (6,28,105)



#and c.benstatus = 'CADASTRO'
#where c.benstatus = 'COLETIVO'
group by c.id order by c.nome;

