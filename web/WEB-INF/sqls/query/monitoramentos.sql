select distinct c.cpf, r.datamon, count(r.datamon) as total from mpm_plano p
inner JOIN  cidadao c ON c.id = p.cidadao_id
inner JOIN  mpm_rendimento r ON p.id = r.plano_id 
group by c.cpf, r.datamon
