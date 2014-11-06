select cid.nome,
c.sexo,
#(year(now())-year(c.datanasc) as idade,
#curdate() - c.datanasc) / 365
case when (year(now())-year(c.datanasc)) <= 20 then
         '    até 20'
          when (year(now())-year(c.datanasc))  between 21 and 30 then
         'de 21 a 30'
         when  (year(now())-year(c.datanasc))  between 31 and 40 then
         'de 31 a 40'
         when  (year(now())-year(c.datanasc))  between 41 and 50 then
         'de 41 a 50'
         when  (year(now())-year(c.datanasc))  between 51 and 60 then
         'de 51 a 60'
         when  (year(now())-year(c.datanasc))  between 61 and 70 then
         'de 61 a 70'
         when  (year(now())-year(c.datanasc))  between 71 and 80 then
         'de 71 a 80'
	when  (year(now())-year(c.datanasc))  between 81 and 90 then
         'de 81 a 90'
  	when (year(now())-year(c.datanasc)) >= 91 then
         'de 91 ...'
          end as faixa_idade,
          count(distinct c.id) as qtd,

sum(eq.valor) as totalinvest
from cidade cid
inner join bairro b on b.cidade_id = cid.id
inner join endereco e on e.bairro_id = b.id
inner join cidadao c ON c.endereco_id = e.id
left JOIN   mci_equipamentossecretaria eq ON eq.cidadao_id = c.id

where cid.estado_id = 1
and c.benstatus = 'BENEFICIADO'
and not ISNULL(c.datanasc)
and c.anodemanda in (2011,2012)
group by cid.nome, c.sexo, faixa_idade
;