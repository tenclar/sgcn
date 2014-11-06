select 
count(`r`.`datamon`) AS `total`
 from ((`mpm_plano` `p` join `cidadao` `c` on((`c`.`id` = `p`.`cidadao_id`)))
  left join `mpm_rendimento` `r` on((`p`.`id` = `r`.`plano_id`))) 
  group by `r`.`plano_id` , c.cpf
 # order by `p`.`datacreated` desc 
 