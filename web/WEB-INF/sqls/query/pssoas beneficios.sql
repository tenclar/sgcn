select c.tipopessoa, c.benstatus,c.statuscid, count(c.id) as total from cidadao c 
group by c.tipopessoa, c.benstatus, c.statuscid