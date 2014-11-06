select count(c.id) 'total' from cidadao c 
where c.tipopessoa = 'CID'
#and c.benstatus <> 'CADASTRO'
 ;

select c.sexo, count(c.id) 'beneficiado' from cidadao c 
where c.tipopessoa = 'CID'
and c.benstatus <> 'CADASTRO'
group by c.sexo
WITH ROLLUP 
 ;

select   sum(e.valor) 'total geral Investido' from mci_equipamentossecretaria e;

select   sum(e.valor) 'total Investido COOP' from mci_equipamentossecretaria e, cidadao c 
where c.id = e.cidadao_id
and c.benstatus <> 'CADASTRO'
and c.tipopessoa = 'COOP'
;

select  c.sexo,  sum(e.valor) 'total Investido CID' from mci_equipamentossecretaria e, cidadao c 
where c.id = e.cidadao_id
and c.benstatus <> 'CADASTRO'
and c.tipopessoa = 'CID'
group by c.sexo 
WITH ROLLUP ;

