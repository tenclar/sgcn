/*
 * Fun��o para adicionar tabula��o via enter
 *
 * Tiago Dantas
 *@ http://www.lordware.com.br/2008/04/29/utilidades-para-aspnet-enter-como-tab/	
 */


var podePost = true; //Vari�vel que cancela o post no firefox

// Fun��o principal
function EnterTab(evt)
{
    evt = getEvent(evt);

    if (getKeyCode(evt) != 13)
        return true;

    var elementAtivo = getTarget(evt);

    if (!elementAtivo)
        return true; // Se no tiver nenhum elemento ativo

    if (!elementAtivo.type)
        return true; // Se no tiver nenhum elemento ativo

    if (elementAtivo.type.toLowerCase() == "submit" || elementAtivo.type.toLowerCase() == "button")
    {
        podePost = true;
        elementAtivo.click();
        return cancelaPost(evt);
    }

    var nextElement = null;

    if (elementAtivo.tabIndex == 0)
        nextElement = getNextElementByName(elementAtivo);
    else
        nextElement = getNextElementByTabIndex(elementAtivo);

    if (nextElement)
    {
        var theForm = document.forms[0];

        if (theForm.addEventListener)
            theForm.addEventListener('submit', enviaForm, false); // Evento submit no form para FireFox

        if (nextElement.type.toLowerCase() == "submit" || nextElement.type.toLowerCase() == "button")
        {
            if (elementAtivo.tabIndex == 0)
                return true;

            podePost = true;
            nextElement.click();

            return cancelaPost(evt);
        }

        podePost = false;
        nextElement.focus();

        return cancelaPost(evt);
    }
    else
    {
        podePost = true;
        return true;
    }
}

// Fun��o para cancelar o envio do form para o FireFox
function enviaForm(evt)
{
    if (!podePost)
    {
        evt.cancelBubble = true;
        evt.returnValue = false;

        if (evt.preventDefault)
            evt.preventDefault();

        if (evt.stopPropagation)
            evt.stopPropagation();

        podePost = true;
        return false;
    }
    else
        return true;
}

function cancelaPost(evt)
{
    evt.cancelBubble = true;
    evt.returnValue = false;

    if (evt.preventDefault)
        evt.preventDefault();

    if (evt.stopPropagation)
        evt.stopPropagation();

    return false;
}

// Recupera o evento do form
function getEvent(evt)
{
    if (!evt)
        evt = window.event; //Internet Explorer
    return evt;
}

// Recupera o elemento que est� com o foco
function getTarget(evt)
{
    var target;

    if (evt.srcElement)
        target = evt.srcElement;
    else if (evt.target)
        target = evt.target;

    return target;
}

// Recupera o c�digo da tecla que foi pressionado
function getKeyCode(evt)
{
    var code;

    if (typeof(evt.keyCode) == 'number')
        code = evt.keyCode;
    else if (typeof(evt.which) == 'number')
        code = evt.which;
    else if (typeof(evt.charCode) == 'number')
        code = evt.charCode;
    else
        return 0;

    return code;
}

// Recupera o elemento deacordo com o TabIndex
function getElementByTabIndex(tabIndex)
{
    var form = document.forms[0];

    for (var i = 0; i < form.elements.length; i++)
    {
        var el = form.elements[i];

        if (el.tabIndex && el.tabIndex == tabIndex)
            return el;
    }

    return null;
}

// Recupera o pr�ximo elemento de acordo com o nome
function getNextElementByTabIndex(elementAtivo)
{
    var targetTabIndex = elementAtivo.tabIndex;
    var nextTabIndex = targetTabIndex + 1;
    var nextElement = getElementByTabIndex(nextTabIndex);

    // Margem de erro
    var i = 0;

    for (i = 0; i < 15; i++) // Toler�ncia de tabIndex
    {
        if (nextElement != null && !nextElement.disabled)
            break;

        nextTabIndex = nextTabIndex + 1;
        nextElement = getElementByTabIndex(nextTabIndex);
    }

    return nextElement;
}

// Recupera o pr�ximo elemento de acordo com o nome
function getNextElementByName(elementAtivo)
{
    var passou = false;
    var form = document.forms[0];

    for (var i = 0; i < form.elements.length; i++)
    {
        var el = form.elements[i];
        if (el && el.id == elementAtivo.id || passou)
        {
            passou = true;
            // Encontrou o elemento atual
            var x = i + 1;
            var elnx = form.elements[x];

            if (elnx)
            {
                switch (elnx.type)
                {
                    case "text":
                    case "submit":
                    case "button":
                    case "reset":
                    case "select-one":
                    case "checkbox":
                    case "image":
                    case "password":
                    case "radio":
                    case "reset":
                    case "submit":
                    case "textarea":
                        if (elnx.disabled)
                            continue;

                        break;
                    default:
                        continue;
                        break;
                }

                return elnx;
            }
        }
    }

    return null;
}


function strPad(palavra, casas, carac, dir) {
    //dir = 'R' => Right; dir = 'L' => Left;
    if (palavra == null || palavra == '')
        palavra = 0;
    var ret = '';
    var nro = casas - (palavra.length);
    for (var i = 0; i < nro; i++)
        ret += carac;
    if (dir == 'R')
        ret = palavra + ret;
    else if (dir == 'L')
        ret += palavra;
    return ret;
}


var letters = ' ABC�DEFGHIJKLMN�OPQRSTUVWXYZabc�defghijklmn�opqrstuvwxyz��������������������������';
var numbers = '1234567890';
var signs = ',.:;@-\'';
var mathsigns = '+-=()*/';
var custom = '<>#$%&?�';
var especiais = '(),-*�';
var navigation = 0;
var backspace = 8;
var enter = 13;

//Fun��o para valida��o de entradas em inputs
function alpha(e, allow) {
    var k;
    k = document.all ? parseInt(e.keyCode) : parseInt(e.which);
    return (allow.indexOf(String.fromCharCode(k)) != -1 || k == navigation
            || k == backspace || k == enter);
}


//Fun��o para contagem regressiva de caracteres restantes
//Ex: usado no onkeydown e onkeydup ao mesmo tempo
//mf: textarea
//cf: campo contador
//m: limite
function limitTextArea(mf, cf, m) {
    if (mf.value.length > m) {
        mf.value = mf.value.substring(0, m);
    }
    else
    if (document.getElementById(cf).value != null) {
        document.getElementById(cf).value = m - mf.value.length;
    }
}


/* Autor: Francisco C Paulino - Tofinha (fcptofinha@globo.com) -->
 Data: 08/11/2002 - 11:55hs -->
 Script que formata Valores em reais ao  digitar -->
 In�cio da Fun��o FormataReais */

function formataReais(fld, milSep, decSep, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13 || whichCode == 0 || whichCode == 8)
        return true;
    key = String.fromCharCode(whichCode);  // Valor para o c�digo da Chave
    if (strCheck.indexOf(key) == -1)
        return false;  // Chave inv�lida
    len = fld.value.length;
    for (i = 0; i < len; i++)
        if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(fld.value.charAt(i)) != -1)
            aux += fld.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        fld.value = '';
    if (len == 1)
        fld.value = '0' + decSep + '0' + aux;
    if (len == 2)
        fld.value = '0' + decSep + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += milSep;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        fld.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            fld.value += aux2.charAt(i);
        fld.value += decSep + aux.substr(len - 2, len);
    }
    return false;
}

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function setaFoco(elemento) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < elemento.form.elements.length; i++)
            if (elemento == elemento.form.elements[i])
                break;
        i = (i + 1) % elemento.form.elements.length;
        elemento.form.elements[i].focus();
        event.preventDefault();
        return false;
    }
    return false;
}
function autoTab(input, e) {
    var ind = 0;
    var isNN = (navigator.appName.indexOf("Netscape") != -1);
    var keyCode = (isNN) ? e.which : e.keyCode;
    var nKeyCode = e.keyCode;
    if (keyCode == 13) {
        if (!isNN) {
            window.event.keyCode = 0;
        } // evitar o beep  
        ind = getIndex(input);
        if (input.form[ind].type == 'textarea') {
            return;
        }
        ind++;
        input.form[ind].focus();
        if (input.form[ind].type == 'text') {
            input.form[ind].select();
        }
    }

    function getIndex(input) {
        var index = -1, i = 0, found = false;
        while (i < input.form.length && index == - 1)
            if (input.form[i] == input) {
                index = i;
                if (i < (input.form.length - 1)) {
                    if (input.form[i + 1].type == 'hidden') {
                        index++;
                    }
                    if (input.form[i + 1].type == 'button' && input.form[i + 1].id == 'tabstopfalse') {
                        index++;
                    }
                }
            }
            else
                i++;
        return index;
    }
}



function MM_preloadImages() { //v3.0
    var d = document;
    if (d.images) {
        if (!d.MM_p)
            d.MM_p = new Array();
        var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
        for (i = 0; i < a.length; i++)
            if (a[i].indexOf("#") != 0) {
                d.MM_p[j] = new Image;
                d.MM_p[j++].src = a[i];
            }
    }
}
function sair(url) {
    window.location.href = url;
}

function njanela (URL){
   		window.open(URL,"janela1",
                                    width=800, 
                                   height=600,
                                   directories=no,
                                   location=no,
                                   menubar=no,
                                   scrollbars=no,
                                   status=no,
                                   toolbar=no,
                                   resizable=no)
	}
function njanela(){
   		//window.open(URL,"janela1")
                this.document.target = '_blank';
                                   
	}