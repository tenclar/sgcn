SELECT DISTINCT
     cidadao.`id` AS cidadao_id,
     cidadao.`apelido` AS cidadao_apelido,
     cidadao.`benstatus` AS cidadao_benstatus,
     cidadao.`cpf` AS cidadao_cpf,
     cidadao.`datacreated` AS cidadao_datacreated,
     cidadao.`nome` AS cidadao_nome,
     cidadao.`rg` AS cidadao_rg,
     cidadao.`sexo` AS cidadao_sexo,
     cidadao.`tipopessoa` AS cidadao_tipopessoa,
     cidadao.`nis` AS cidadao_nis,
     endereco.`cep` AS endereco_cep,
     bairro.`nome` AS bairro_nome,
     bairro.`zona` AS bairro_zona,
     endereco.`logradouro` AS endereco_logradouro,
     cidade.`id` AS cidade_id,
     cidade.`cep` AS cidade_cep,
     cidade.`nome` AS cidade_nome,
     cidade.`estado_id` AS cidade_estado_id,
     cidadao.`numero` AS cidadao_numero,
      cidadao.publico_id
FROM
     `bairro` bairro INNER JOIN `endereco` endereco ON bairro.`id` = endereco.`bairro_id`
     INNER JOIN `cidadao` cidadao ON endereco.`id` = cidadao.`endereco_id`
	  INNER JOIN `cidade` cidade ON bairro.`cidade_id` = cidade.`id`
WHERE
     cidade.`estado_id` = 1
/* AND bairro.`zona` = 'RURAL' */ 
 AND cidadao.ramoempreendimento_id = 14
/* AND cidadao.publico_id = 3 */
 AND cidadao.`tipopessoa` = 'CID'

ORDER BY
     cidadao.nome ASC