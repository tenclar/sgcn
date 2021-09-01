select * from mci_cidassociados ass
inner join cidadao ci on ci.id = ass.associado_id
where ass.cidadao_id in (
select c.id from cidadao c
inner join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
 where c.tipopessoa = 'GRUPO'  
and c.benstatus = 'RESERVA'
GROUP BY c.nome)
group by ci.nome