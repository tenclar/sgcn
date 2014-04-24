/*
 * Funções de mascaras crossbrowser com expressões regulares
 * Desenvolvido por: Tenclar valus
 * Baseado no artigo de Elcio Ferreira (http://elcio.com.br/ajax/mascara/) 
 
 
*/

function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}


//<input id="inumeros" onkeypress="mascara(this,soNumeros)" />
function soNumeros(v){
    return v.replace(/\D/g,"")
}


//<input id="iletras" onkeypress="mascara(this,soLetras)" />
function soLetras(v){
    return v.replace(/\d/g,"") //Remove tudo o que não é Letra
}


//<input id="inumeros" onkeypress="mascara(this,soNumerosReais)" />
function soNumerosReais(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/(\d{0,})(\d{2})/,"$1,$2")  
    return v
}

//<input id="itelefone" onkeypress="mascara(this,telefone)" maxlength="14" />
function telefone(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") 
    v=v.replace(/(\d{4})(\d)/,"$1-$2")    
    return v
}

//<input id="icpf" onkeypress="mascara(this,cpf)" maxlength="14" />
function cpf(v){
    v=v.replace(/\D/g,"")                    
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") 
    return v
}

//<input id="icep" onkeypress="mascara(this,cep)" maxlength="9" />
function cep(v){
    v=v.replace(/D/g,"")                
    v=v.replace(/^(\d{5})(\d)/,"$1-$2") 
    return v
}

//<input id="icnpj" onkeypress="mascara(this,cnpj)" maxlength="18" />
function cnpj(v){
    v=v.replace(/\D/g,"")                           
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")             
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3") 
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2")           
    v=v.replace(/(\d{4})(\d)/,"$1-$2")              
    return v
}

//<input id="iromanos" onkeypress="mascara(this,romanos)" maxlength="18" />
function romanos(v){
    v=v.toUpperCase()             
    v=v.replace(/[^IVXLCDM]/g,"") 
    //http://www.diveintopython.org/refactoring/refactoring.html
    while(v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/,"")!="")
        v=v.replace(/.$/,"")
    return v
}

//<input id="isite" onkeyup="mascara(this,site)" value="http://" />
function site(v){
    v=v.replace(/^http:\/\/?/,"")
    dominio=v
    caminho=""
    if(v.indexOf("/")>-1)
        dominio=v.split("/")[0]
    caminho=v.replace(/[^\/]*/,"")
    dominio=dominio.replace(/[^\w\.\+-:@]/g,"")
    caminho=caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g,"")
    caminho=caminho.replace(/([\?&])=/,"$1")
    if(caminho!="")dominio=dominio.replace(/\.+$/,"")
    v="http://"+dominio+caminho
    return v
}
function valor(v){
    v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
    //v=v.replace(/[0-9]{12}/,"invalido")   //limita pra maximo 999.999.999,99
    v=v.replace(/(\d{1})(\d{8})$/,"$1.$2")  //coloca ponto antes dos ultimos 8 digitos
    v=v.replace(/(\d{1})(\d{5})$/,"$1.$2")  //coloca ponto antes dos ultimos 5 digitos
    v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2")        //coloca virgula antes dos ultimos 2 digitos
    return v
}

/**************************************************************************
Função para simular um Tab quando for pressionado a tecla Enter
Exemplo: onKeyDown="TABEnter()"
Funciona em TEXT BOX,RADIO BUTTON, CHECK BOX e menu DROP-DOWN
**************************************************************************/
function tabEnter(oEvent){
    var oEvent = (oEvent)? oEvent : event;
    var oTarget =(oEvent.target)? oEvent.target : oEvent.srcElement;
    if(oEvent.keyCode==13)
        oEvent.keyCode = 9;
    if(oTarget.type=="text" && oEvent.keyCode==13)
        //return false;
        oEvent.keyCode = 9;
    if (oTarget.type=="radio" && oEvent.keyCode==13)
        oEvent.keyCode = 9;
}

//<input type="text" name="data" maxlength="10" onkeyup="Formatadata(this,event)" />
		
function Formatadata(Campo, teclapres)
{
    var tecla = teclapres.keyCode;
    var vr = new String(Campo.value);
    vr = vr.replace("/", "");
    vr = vr.replace("/", "");
    vr = vr.replace("/", "");
    tam = vr.length + 1;
    if (tecla != 8 && tecla != 8)
    {
        if (tam > 0 && tam < 2)
            Campo.value = vr.substr(0, 2) ;
        if (tam > 2 && tam < 4)
            Campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2);
        if (tam > 4 && tam < 7)
            Campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2) + '/' + vr.substr(4, 7);
    }
}
//<INPUT TYPE="text" NAME="Data" onblur="validaDat(this,this.value)">

				
function validaDat(campo,valor) {
	var date=valor;
	var ardt=new Array;
	var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
	ardt=date.split("/");
	erro=false;
	if ( date.search(ExpReg)==-1){
		erro = true;
		}
	else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
		erro = true;
	else if ( ardt[1]==2) {
		if ((ardt[0]>28)&&((ardt[2]%4)!=0))
			erro = true;
		if ((ardt[0]>29)&&((ardt[2]%4)==0))
			erro = true;
	}
	if (erro) {
		alert("\"" + valor + "\" não é uma data válida!!!");
		campo.focus();
		campo.value = "";
		return false;
	}
	return true;
}

function handleComplete(args){                    
    if(!args.validationFailed){
        if(args.success==false) {
                         
            dlgCidNew.hide();    
            cadlist.close();
            cadins.show();
            document.getElementById('cidapelido').focus();
        }
    }                 
                  
}
              

    
   



