select c.benstatus, c.statuscid, c.tipopessoa, count(c.id) as total  FROM cidadao c 
 INNER JOIN endereco e ON c.endereco_id = e.id
     INNER JOIN  bairro b ON e.bairro_id = b.id 
          INNER JOIN  cidade ci ON b.cidade_id = ci.id 

AND ci.id = 6
group by c.benstatus, c.statuscid, c.tipopessoa


