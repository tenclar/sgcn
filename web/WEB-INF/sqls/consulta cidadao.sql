select * from cidadao c where c.statuscid = 'ASSOCIADO' and c.benstatus = 'INDIVIDUAL';
select * from mci_cidassociados cidass where  cidass.associado_id = 300;

select  c.id,c.cpf, c.nome,  c.statuscid, c.benstatus AS statusCidadao, ce.benstatus as statusempresa from mci_cidassociados cidass1 
inner JOIN  cidadao c ON c.id = cidass1.associado_id
inner JOIN  cidadao ce ON ce.id = cidass1.cidadao_id
#where  ce.benstatus = 'COLETIVO'
group by c.id;


select * from cidadao ci where ci.id = 4446;
select * from mci_equipamentossecretaria esec where esec.cidadao_id = 3729;
