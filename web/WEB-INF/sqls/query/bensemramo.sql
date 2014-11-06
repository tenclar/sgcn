select c.cpf, c.nome,c.ramoempreendimento_id from cidadao c where ISNULL(c.ramoempreendimento_id) 
and c.tipopessoa = 'CID' and c.benstatus = 'INDIVIDUAL'
;