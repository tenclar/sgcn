UPDATE cidadao c 
#left join mci_equipamentossecretaria eq on eq.cidadao_id = c.id
#inner join endereco e on e.id = c.endereco_id
#inner join bairro b on b.id = e.bairro_id
#inner join cidade cid on cid.id = b.cidade_id
#inner join  mci_ramoempreendimento ramo ON ramo.id = c.ramoempreendimento_id
SET c.statuscid = 'COLETIVO'
 WHERE c.statuscid = 'ASSOCIADO'
 #ISNULL(c.anodemanda)
  #and cid.estado_id <> 1
#  c.tipopessoa = 'CID'
#AND c.benstatus = 'CADASTRO'
#and cid.id = 22
#and c.anodemanda = '2013'
#AND  YEAR(eq.dataentrega) = 2013
#AND YEAR(c.datacreated) = 2011
#and cid.estado_id <> 1
;
