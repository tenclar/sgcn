# total de monitorados  pessoa fisica e juridica
select c.tipopessoa, count(p.id) 
from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
inner JOIN  mpm_rendimento r ON r.plano_id = p.id
group by c.tipopessoa WITH ROLLUP  ;

