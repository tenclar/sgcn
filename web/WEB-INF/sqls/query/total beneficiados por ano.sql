#total de monitoramento por beneficiário
select
	  YEAR(c.datacreated) as ano,
	  count(c.id) as totalben
from cidadao c
#inner join mci_equipamentossecretaria eq on eq.cidadao_id = c.id

where c.tipopessoa = 'CID'
#and c.statuscid = 'INDIVIDUAL'
#and c.benstatus = 'INDIVIDUAL'
and c.benstatus <> 'CADASTRO'
#AND  YEAR(eq.dataentrega) = 2011

GROUP BY
    YEAR(c.datacreated)
WITH ROLLUP
;