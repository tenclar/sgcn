select sum(encubacoes) from(select count(distinct rd.datamon) as encubacoes 
from mpm_plano p 
inner join mpm_rendimento rd on p.id = rd.plano_id
inner join cidadao c on c.id = p.cidadao_id
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
where c.benstatus = 'BENEFICIADO' and c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'
 and  not ISNULL(rd.datamon)
group by cid.nome, rd.plano_id) as a

