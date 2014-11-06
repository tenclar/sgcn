select c.datacreated,  c.cpf,c.nis, c.nome, e.logradouro, b.nome as bairros , ci.nome as Cidade  FROM cidadao c 
 INNER JOIN endereco e ON c.endereco_id = e.id
     INNER JOIN  bairro b ON e.bairro_id = b.id 
          INNER JOIN  cidade ci ON b.cidade_id = ci.id 
where c.tipopessoa = 'CID' 
AND b.id = 48602
group by c.datacreated 
order by c.datacreated desc