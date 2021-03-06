set @som=0, @rendapercapta = 0.0, @disp_atend=0,@cap_atend=0,@qtdbolsa=0;
/*select @som:=(sum(bolsafamilia.`qtd`)) as somabolsa from bolsafamilia inner join cidades on bolsafamilia.cidades_id = cidades.id ;*/

SELECT  
	
	 cidades.`id` AS cidades_id,     
     cidades.`nome` AS cidades_nome,          
   @qtdbolsa:=	bolsafamilia.`qtd` AS bolsafamilia_qtd,
   recurso.descricao,
   beneficiados.`id`,
   (select sum(bolsafamilia.`qtd`) as soma from bolsafamilia where cidades.id = beneficiados.cidade_id ) AS somabolsafamilia,
   @rendapercapta:= CAST(recurso.valorrecurso / (select sum(bolsafamilia.`qtd`) as soma3 from bolsafamilia where cidades.id = beneficiados.cidade_id ) as decimal(6,2) ) AS rendapercapta, 
	/*	@rendapercapta:=CAST(recurso.valorrecurso / @som as decimal(15,2))as so ,	*/
     	(select sum(cadunico.`qtd`) as soma2 from cadunico where  cidades.id = beneficiados.cidade_id)  AS sumcadunico,		
     	(select sum(populacao.`urbana`) as soma from populacao where cidades.id = beneficiados.cidade_id) AS sumUrbana,
   	(select sum(populacao.`rural`) as soma from populacao where cidades.id = beneficiados.cidade_id) AS sumrural,
   	@disp_atend:=(select cast(@rendapercapta * bolsafamilia.qtd as decimal(15,2)) as Invest from bolsafamilia where cidades.id=bolsafamilia.cidades_id) as disponivelAtendimento,

   	@cap_atend:=cast(@disp_atend/recurso.valorbeneficio as decimal(15,0)) as Cap_atend,
	  	(@cap_atend / bolsafamilia.`qtd`)*100 as Cap_perc,
	   	cidades.`meta` as meta, 
	  	(cidades.`meta`/bolsafamilia.`qtd`)*100 as percmeta,
	  	(cidades.`meta`- @cap_atend) as saldo,
  		bolsafamilia.`datapesquisa` AS bolsafamilia_datapesquisa        
		/*recurso.valorbeneficio as valorben,
     	cadunico.`qtd` AS cadunico_qtd,
		populacao.`urbana` AS populacao_urbana,
		populacao.`rural` AS populacao_rural,    		
     	cadunico.`datapesquisa` AS cadunico_datapesquisa,    
	  	populacao.`datapesquisa` AS populacao_datapesquisa*/
         
FROM
     `beneficiados` beneficiados

	   inner JOIN `recurso` recurso ON beneficiados.`recurso_id` = recurso.`id`     
		 right outer JOIN `cidades` cidades ON beneficiados.`cidade_id` = cidades.`id`	
      inner JOIN `bolsafamilia` bolsafamilia ON cidades.`id` = bolsafamilia.`cidades_id`      
      /*left outer JOIN `cadunico` cadunico ON beneficiados.`cidade_id` = cadunico.`cidades_id`
     	left outer JOIN `populacao` populacao ON beneficiados.`cidade_id` = populacao.`cidades_id`*/
      
where cidades.estado_id = 1

GROUP BY 
cidades.nome

