SELECT
     recurso.`id` AS recurso_id,
     recurso.`datacreated` AS recurso_datacreated,
     recurso.`descricao` AS recurso_descricao,
     recurso.`valorbeneficio` AS recurso_valorbeneficio,
     recurso.`valorrecurso` AS recurso_valorrecurso,
     beneficiados.`id` AS beneficiados_id,
     beneficiados.`cidades_id` AS beneficiados_cidades_id,
     beneficiados.`pessoas_id` AS beneficiados_pessoas_id,
     beneficiados.`recurso_id` AS beneficiados_recurso_id,
     cidades.`id` AS cidades_id,
     cidades.`estado_id` AS cidades_estado_id,
     cidades.`nome` AS cidades_nome,
     cidades.`meta` AS cidades_meta,
     cadunico.`id` AS cadunico_id,
     cadunico.`cidades_id` AS cadunico_cidades_id,
     cadunico.`qtd` AS cadunico_qtd,
     cadunico.`datapesquisa` AS cadunico_datapesquisa,
     bolsafamilia.`id` AS bolsafamilia_id,
     bolsafamilia.`cidades_id` AS bolsafamilia_cidades_id,
     bolsafamilia.`qtd` AS bolsafamilia_qtd,
     bolsafamilia.`datapesquisa` AS bolsafamilia_datapesquisa,
     populacao.`id` AS populacao_id,
     populacao.`cidades_id` AS populacao_cidades_id,
     populacao.`urbana` AS populacao_urbana,
     populacao.`rural` AS populacao_rural,
     populacao.`datapesquisa` AS populacao_datapesquisa
FROM
     `recurso` recurso INNER JOIN `beneficiados` beneficiados ON recurso.`id` = beneficiados.`recurso_id`
     LEFT OUTER JOIN `cidades` cidades ON beneficiados.`cidades_id` = cidades.`id`
   	LEFT OUTER JOIN `cadunico` cadunico ON cidades.`id` = cadunico.`cidades_id`
     LEFT OUTER JOIN `bolsafamilia` bolsafamilia ON cidades.`id` = bolsafamilia.`cidades_id`
     LEFT OUTER JOIN `populacao` populacao ON cidades.`id` = populacao.`cidades_id`
WHERE
     recurso.`descricao` = 'proprio'
     
GROUP BY 
		cidades.`id`