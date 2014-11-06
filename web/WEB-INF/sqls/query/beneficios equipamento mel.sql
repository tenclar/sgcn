select c.id, c.nome, c.cpf as CNPJ, c.benstatus AS StatusBeneficio, remp.nome ,
(select count(e.cidadao_id) from mci_equipamentossecretaria e where e.cidadao_id = c.id) as total_Equipamentos  

from cidadao c, mci_ramoempreendimento remp
where c.tipopessoa= 'CID'
and c.ramoempreendimento_id = remp.id
and c.ramoempreendimento_id  in (6,28,105)



#and c.benstatus = 'CADASTRO'
#where c.benstatus = 'COLETIVO'
group by c.benstatus , c.id
order by  c.benstatus asc, c.nome asc ;

