select count(distinct(eq.cidadao_id))  from  mci_equipamentossecretaria eq 
where   YEAR(eq.dataentrega)  between 2015 and 2016

 ;