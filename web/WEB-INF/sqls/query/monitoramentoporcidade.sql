#total de monitoramento por beneficiário
select  
	  c.benstatus,	
     cid.nome as cidade,
     ramo.nome as ramo,     
     c.id,
     c.nome as cidadao,
     c.cpf AS cpf,
     c.endereco_id,
     e.logradouro ,
     c.numero,
	  b.nome 'Bairro',
     pub.nome as publico,
    (SELECT count(eq.cidadao_id) as totale FROM `mci_equipamentossecretaria` eq WHERE eq.cidadao_id = c.id ) as totalequipamento,
	 (select count(distinct r.datamon) as totalm from mpm_rendimento r where r.plano_id = p.id) as totalmonitoramento
from mpm_plano p 
inner join cidadao c ON c.id = p.cidadao_id 
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
inner join mci_publico pub ON  pub.id = c.publico_id
inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
#left join mpm_rendimento r ON p.id = r.plano_id 
where c.tipopessoa = 'CID'
and c.statuscid = 'INDIVIDUAL'

GROUP BY
      cid.nome, ramo.nome, p.id
         #ramo.nome, p.id
         #p.id
ORDER BY
       cid.nome asc, ramo.nome asc
 #ramo.nome asc
;