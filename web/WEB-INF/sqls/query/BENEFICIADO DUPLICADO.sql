-- select * from cidadao where cidadao.cpf = '67085148268';
-- select * from mci_equipamentossecretaria e where e.cidadao_id = 1873;
#select c.nome from cidadao c  inner join mci_cidassociados ass on c.id = ass.cidadao_id where ass.associado_id = 4268;
  
-- select * from mci_cidassociados ass where ass.associado_id = 294  ;
-- select * from mci_cidassociados ass where ass.associado_id = 294  ;
-- 
-- 
-- 
-- select  cp.nome,c.nome, c.statuscid, c.benstatus, c.id as cid, cp.id as coop, count(c.id) as qtdcad   from cidadao cp
-- inner join mci_cidassociados ass on ass.cidadao_id = cp.id
-- inner join cidadao c on c.id = ass.associado_id
-- 
-- inner join endereco e on e.id = cp.endereco_id
-- inner join bairro b on b.id = e.bairro_id
-- inner join cidade cid on cid.id = b.cidade_id
-- 
-- where c.benstatus = 'BENEFICIADO'
-- AND c.statuscid = 'COLETIVO'
-- and cid.estado_id = 1
-- group by c.nome
-- having count(ass.associado_id)>1 ;
-- 
-- select  ass.cidadao_id, ass.associado_id, count(ass.associado_id) from mci_cidassociados ass
-- 
-- group by ass.cidadao_id, ass.associado_id
-- having count(ass.associado_id) > 1;

select * from mci_cidassociados ass where ass.cidadao_id = 532;
-- select * from cidadao c where c.id = 303 ;
-- select * from cidadao c where c.id = 304 ;
-- select * from cidadao c where c.id = 305 ;
-- select * from cidadao c where c.id = 311 ;
-- select * from cidadao c where c.id = 320 ;
-- select * from cidadao c where c.id = 732 ;
-- select * from cidadao c where c.id = 334 ;
-- select * from cidadao c where c.id = 328 ;
-- select * from cidadao c where c.id = 339 ;
select * from cidadao c where c.id = 286 ;
select * from cidadao c where c.id = 298 ;
select * from cidadao c where c.id = 300 ;
select * from cidadao c where c.id = 302 ;
select * from cidadao c where c.id = 309 ;