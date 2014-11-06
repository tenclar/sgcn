# total de monitorados  pessoa fisica e juridica
#select count(p.id) as totalm 
#from mpm_plano p
#inner join cidadao c on c.id = p.cidadao_id
#where c.tipopessoa = 'COOP'
#;



# total de monitorados  pessoa fisica e juridica
select
c.cpf,
c.nome,
count(distinct r.datamon) as monitoramentos,
count(distinct ass.associado_id) as associados,
count(distinct eq.id) as equipamentos
#,    (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequipamento
#, (select count(distinct r.datamon) as totalm from mpm_rendimento r where r.plano_id = p.id) as totalmonitoramento
#,(select count(ass.id) from mci_cidassociados ass where ass.cidadao_id = p.cidadao_id) as associadoss
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
inner JOIN  mci_cidassociados ass ON ass.cidadao_id = c.id
left JOIN  mci_equipamentossecretaria eq ON eq.cidadao_id = p.cidadao_id 
inner JOIN  mpm_rendimento r ON r.plano_id = p.id
where c.tipopessoa = 'COOP'
group by c.nome
with ROLLUP
   ;
# total de encubações realizadas


select sum(q.total)as totalgeral from qtd_mon_por_cpf q;




#total inativa
select c.tipopessoa, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
left JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) <=0
group by c.tipopessoa  ;

#total ativa

select c.tipopessoa, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) >0
group by c.tipopessoa  ;

# EMPREENDIMENTOS ATIVO por pessoa
select c.tipopessoa, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
# JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) >0
group by c.tipopessoa  ;

# EMPREENDIMENTOS INATIVO por pessoa
select c.tipopessoa, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
#JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) <=0
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
select c.sexo, count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
# JOIN  mpm_rendimento r ON r.plano_id = p.id
where (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id) >0 and c.tipopessoa = 'CID'
group by c.sexo  ;


select count(p.id) total
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
# JOIN  mpm_rendimento r ON r.plano_id = p.id
where p.id not in (select rs.plano_id  from mpm_rendimento rs )
;

#select c.tipopessoa, count(p.id) as total
#from mpm_plano p 
#inner JOIN  cidadao c ON c.id = p.cidadao_id 
#left JOIN  mpm_rendimento r ON r.plano_id = p.id
#group by c.tipopessoa WITH ROLLUP  ;

#select c.tipopessoa, count(p.id) 
#from mpm_plano p 
#inner JOIN  cidadao c ON c.id = p.cidadao_id 
#inner JOIN  mpm_rendimento r ON r.plano_id = p.id
#group by c.tipopessoa WITH ROLLUP  ;

#select c.tipopessoa, sum(r.vendas) as vendas, sum(r.despesas) as despesas ,sum(r.vendas - r.despesas) as lucro 
#from mpm_plano p 
#inner JOIN  cidadao c ON c.id = p.cidadao_id 
#left JOIN  mpm_rendimento r ON r.plano_id = p.id
#group by c.tipopessoa  ;

# total sem rendimentos nas encubações ATIVO INATIVO

#select count(p.id) as total
#from mpm_plano p 
#inner JOIN  cidadao c ON c.id = p.cidadao_id ;