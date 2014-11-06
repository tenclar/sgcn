# Empreendimentos ativo por sexo
select 
	cid.nome,
case when (select sum(rs.vendas) as vendas from mpm_rendimento rs where rs.plano_id = p.id and not ISNULL(rs.vendas)) > 0.0 then  
			'ATIVO'
	  when (select sum(rs.vendas) as venda from mpm_rendimento rs where rs.plano_id = p.id and not ISNULL(rs.vendas)) <= 0.0 then  
	  		'INATIVO'

	  		END as situacao,
	  	
	  		 count(distinct r.datamon) as encubaca,
	  		 

count(distinct p.id) empreeendimentos

from mpm_plano p 
inner JOIN  cidadao c ON c.id = p.cidadao_id 
left JOIN  mpm_rendimento r ON r.plano_id = p.id
inner join endereco e on e.id = c.endereco_id
inner join bairro b on b.id = e.bairro_id
inner join cidade cid on cid.id = b.cidade_id
where  c.tipopessoa = 'CID' 
and cid.estado_id = 1 and not ISNULL(r.datamon)
#and c.benstatus = 'BENEFICIADO'
group by  cid.nome, situacao
#order by  cid.nome, situacao

;