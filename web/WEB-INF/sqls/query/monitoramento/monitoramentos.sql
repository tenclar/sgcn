select distinct c.cpf,  count(distinct r.datamon) as encubacoes from mpm_plano p
inner JOIN  cidadao c ON c.id = p.cidadao_id
inner JOIN  mpm_rendimento r ON p.id = r.plano_id 
WHERE c.tipopessoa = 'CID' and  not ISNULL(r.datamon)
group by p.id
;

#select distinct `c`.`cpf` AS `cpf`, c.tipopessoa,  count(distinct `r`.`datamon`) AS `total` from ((`mpm_plano` `p` join `cidadao` `c` on((`c`.`id` = `p`.`cidadao_id`))) join `mpm_rendimento` `r` on((`p`.`id` = `r`.`plano_id`))) group by `c`.`cpf`
