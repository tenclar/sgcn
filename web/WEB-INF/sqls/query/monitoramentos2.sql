#total de monitoramento por beneficiário
select distinct c.nome, c.cpf, p.datacreated, count(distinct r.datamon) as total
from mpm_plano p 
inner join cidadao c ON c.id = p.cidadao_id 
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mpm_rendimento r ON p.id = r.plano_id 
where c.tipopessoa = 'CID'
#and c.statuscid = 'INDIVIDUAL'
group by r.plano_id, c.cpf 

order by p.datacreated desc
;