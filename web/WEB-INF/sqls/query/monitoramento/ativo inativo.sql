

# total de encubações realizadas

select sum(q.encubacoes)'Encubações' from qtd_mon_por_cpf q;


# total de monitorados  pessoa fisica 
select count(distinct p.id) 'monitorados'
from mpm_plano p
inner join cidadao c on c.id = p.cidadao_id
left JOIN  mpm_rendimento r ON r.plano_id = p.id
#where c.tipopessoa = 'cid'
;


# EMPREENDIMENTOS ATIVO por tipo pessoa
select c.tipopessoa, count(distinct p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
#inner JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) >0
group by c.tipopessoa  ;

# EMPREENDIMENTOS INATIVO por tipo pessoa
select c.tipopessoa, count(distinct p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
#left JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) =0
or  p.id not in (select rs.plano_id  from mpm_rendimento rs )
group by c.tipopessoa  ;

# Empreendimentos inativo por sexo
select c.sexo, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
# JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) <=0 
or  p.id not in (select rs.plano_id  from mpm_rendimento rs )
and c.tipopessoa = 'CID'
group by c.sexo  ;

# Empreendimentos ativo por sexo
select c.sexo, count(distinct p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
# JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) >0 and c.tipopessoa = 'CID'
group by c.sexo  ;

-- 
-- select count(p.id) total
-- from mpm_plano p
-- inner JOIN  cidadao c ON c.id = p.cidadao_id
-- # JOIN  mpm_rendimento r ON r.plano_id = p.id
-- where p.id not in (select rs.plano_id  from mpm_rendimento rs )
-- ;
-- 


# total de monitorados  pessoa fisica e juridica lista
-- select
-- c.nome,
-- #c.cpf,
-- 
-- count(distinct r.datamon) as monitoramentos,
-- count(distinct ass.associado_id) as associados,
-- count(distinct eq.id) as equipamentos
-- #,    (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequipamento
-- #, (select count(distinct r.datamon) as totalm from mpm_rendimento r where r.plano_id = p.id) as totalmonitoramento
-- #,(select count(ass.id) from mci_cidassociados ass where ass.cidadao_id = p.cidadao_id) as associadoss
-- from mpm_plano p
-- inner JOIN  cidadao c ON c.id = p.cidadao_id
-- inner JOIN  mci_cidassociados ass ON ass.cidadao_id = c.id
-- left JOIN  mci_equipamentossecretaria eq ON eq.cidadao_id = p.cidadao_id
-- inner JOIN  mpm_rendimento r ON r.plano_id = p.id
-- where c.tipopessoa = 'COOP'
-- group by c.nome
-- with ROLLUP
--    ;


