SELECT
     pessoafisica.`id` AS pessoafisica_id,
     pessoafisica.`cpf` AS pessoafisica_cpf,
     pessoafisica.`nis` AS pessoafisica_nis,
     pessoafisica.`rg` AS pessoafisica_rg,
     pessoafisica.`pessoas_id` AS pessoafisica_pessoas_id,
     pessoas.`id` AS pessoas_id,
     pessoas.`endereco` AS pessoas_endereco,
     pessoas.`nome` AS pessoas_nome,
     pessoas.`numero` AS pessoas_numero,
     pessoas.`telefone` AS pessoas_telefone,
     pessoas.`tipopessoa` AS pessoas_tipopessoa,
     pessoas.`bairros_id` AS pessoas_bairros_id
FROM
     `pessoafisica` pessoafisica INNER JOIN `pessoas` pessoas ON pessoafisica.`pessoas_id` = pessoas.`id`
WHERE
     pessoafisica.`id` NOT IN ( SELECT associados.`pessoafisica_id` AS associados_pessoafisica_id FROM `associados` associados )