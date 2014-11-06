# total de monitoramentos
select c.tipopessoa, count(p.id) from mpm_plano p inner JOIN  cidadao c ON c.id = p.cidadao_id group by c.tipopessoa WITH ROLLUP  ;


select sum(q.total)as totalgeral from qtd_mon_por_cpf q;