UPDATE cidadao c SET c.benstatus = 'INDIVIDUAL'
 WHERE c.tipopessoa = 'CID'
AND c.statuscid = 'INDIVIDUAL'
AND c.benstatus = 'COLETIVO'