create procedure altera()
begin
DECLARE done DEFAULT FALSE;
DECLARE a int;
DECLARE cc bigint;
DECLARE cur1 CURSOR FOR select distinct(c.id),c.cpf from cidadao c , cidadao_dependente cd where cd.cidadao_id = c.id;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

#OPEN cur1;

read_loop: LOOP

FETCH cur1 INTO a , cc;
IF done  THEN
LEAVE read_loop;
END IF;

 update cidadao_dependente cdd set cdd.cidadao_cpf = cc where c.id = a ;
 
 
end LOOP;