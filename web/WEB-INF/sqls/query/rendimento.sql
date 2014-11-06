select distinct r.plano_id, r.datamon, count(r.plano_id) as totaldata from mpm_rendimento r
group by r.plano_id, r.datamon
