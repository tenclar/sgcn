select c.nome, t.nome, tl.nome  from cidadao c, mgc_turcidadaos tc , mgc_turma t , mgc_turmalocal tl
where c.cpf = '91531659268' 
and tc.cidadao_id = c.id
and tc.turma_id = t.id;
