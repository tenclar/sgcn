set @som=0, @rendapercapta = 0.0, @dispat=0,@capat=0,@perccap=0;
/*select @som:=(sum(bolsafamilia.`qtd`)) as somabolsa from bolsafamilia inner join cidades on bolsafamilia.cidades_id = cidades.id ;*/

SELECT  
	
	 cidades.`id` AS cidades_id,     
     cidades.`nome` AS cidades_nome,          
   	cidades.`meta` as meta, 
   (select @som:=sum(bolsafamilia.`qtd`) as soma from bolsafamilia where cidades.id = beneficiados.cidade_id ) AS sumbolsafamilia,
   /* (select sum(bolsafamilia.`qtd`) as soma3 from bolsafamilia where cidades.id = beneficiados.cidade_id ) as decimal(6,2) ) AS rendapercapta, */
		@rendapercapta:= CAST(recurso.valorrecurso / @som as decimal(15,2)) as rendapercapta, 
   	@dispat:=(select cast(@rendapercapta * bolsafamilia.qtd as decimal(15,2)) as Invest from bolsafamilia where cidades.id=bolsafamilia.cidades_id) as invescidade,
   	@capat:=cast(@dispat/recurso.valorbeneficio as decimal(15,2)) as dispatend,
	  	@perccap:=cast(@capat/bolsafamilia.`qtd` as decimal(15,2)) as capporcentagem,
	  	cast(cidades.`meta`/bolsafamilia.`qtd` as decimal(15,2)) as percmeta,
	  	(cidades.`meta`-@capat) as saldo,
   	bolsafamilia.`qtd` AS bolsafamilia_qtd,
		bolsafamilia.`datapesquisa` AS bolsafamilia_datapesquisa       
     	
         
FROM
     `cidades` cidades 
      inner JOIN `bolsafamilia` bolsafamilia ON cidades.`id` = bolsafamilia.`cidades_id`            
     	left outer JOIN `beneficiados` beneficiados ON cidades.`id` = beneficiados.`cidade_id`
      inner join `recurso` recurso ON beneficiados.`recurso_id` = recurso.`id`
where cidades.estado_id = 1

GROUP BY 
cidades.id

