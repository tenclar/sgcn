SELECT
     c.benstatus AS statusben,
     cid.nome 'cidade',
     ramo.nome 'ramo',	
     c.id,
     c.nome,
     c.cpf AS cpf,
     e.logradouro ,
	  b.nome 'Bairro',   
     pub.nome as publico,
     ( SELECT count(eq.cidadao_id) FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequipamento
FROM cidadao c
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mci_publico pub ON  pub.id = c.publico_id
inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
WHERE
     c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'

GROUP BY
     c.benstatus,  cid.nome, ramo.nome, c.id
ORDER BY
     c.benstatus DESC,  cid.nome asc, ramo.nome asc 
	  ;