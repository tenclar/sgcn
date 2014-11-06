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
     regional.`nome` AS regional_nome,
     bairro.`zona` AS bairro_zona,
     endereco.`logradouro` AS endereco_logradouro
FROM
     `bairro` bairro INNER JOIN `regional` regional ON bairro.`regional_id` = regional.`id`
     INNER JOIN `endereco` endereco ON endereco.`bairro_id` =  bairro.`id`
     INNER JOIN `cidadao` cidadao ON endereco.`id` = cidadao.`endereco_id`
WHERE
     regional.`id` = 3
 AND cidadao.`tipopessoa` = 'CID'
 AND bairro.cidade_id = 16