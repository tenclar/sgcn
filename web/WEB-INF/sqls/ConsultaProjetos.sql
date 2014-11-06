SELECT
     recurso.`id` AS recurso_id,
     recurso.`datacreated` AS recurso_datacreated,
     recurso.`descricao` AS recurso_descricao,   
     recurso.`valorrecurso` AS recurso_valorrecurso,
     cidades.`id` AS cidades_id,
     cidades.`estado_id` AS cidades_estado_id,
     cidades.`nome` AS cidades_nome,
     beneficiados.`id` AS beneficiados_id,
     beneficiados.`cidades_id` AS beneficiados_cidades_id,
     beneficiados.`pessoas_id` AS beneficiados_pessoas_id,
     beneficiados.`recurso_id` AS beneficiados_recurso_id,
     bolsafamilia.`id` AS bolsafamilia_id,
     bolsafamilia.`qtd` AS bolsafamilia_qtd,
     bolsafamilia.`datapesquisa` AS bolsafamilia_datapesquisa,
     cadunico.`id` AS cadunico_id,
     cadunico.`qtd` AS cadunico_qtd,
     cadunico.`datapesquisa` AS cadunico_datapesquisa,
     populacao.`id` AS populacao_id,
     populacao.`urbana` AS populacao_urbana,
     populacao.`rural` AS populacao_rural,
     populacao.`datapesquisa` AS populacao_datapesquisa,
     pessoas.`id` AS pessoas_id,
     pessoas.`nome` AS pessoas_nome,
     pessoas.`tipopessoa` AS pessoas_tipopessoa
     
FROM
     `recurso` recurso INNER JOIN `beneficiados` beneficiados ON recurso.`id` = beneficiados.`recurso_id`
     RIGHT OUTER JOIN `cidades` cidades ON beneficiados.`cidades_id` = cidades.`id`
     INNER JOIN `pessoas` pessoas ON beneficiados.`pessoas_id` = pessoas.`id`
     INNER JOIN `bolsafamilia` bolsafamilia ON cidades.`id` = bolsafamilia.`cidades_id`
     INNER JOIN `cadunico` cadunico ON cidades.`id` = cadunico.`cidades_id`
     INNER JOIN `populacao` populacao ON cidades.`id` = populacao.`cidades_id`
WHERE
     recurso.`descricao` = 'proprio'
