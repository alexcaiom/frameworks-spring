/**
 * 
 */

var funcionalidade = {};

funcionalidade.init = function() {
	funcionalidade.listar();
	funcionalidade.initEventos();
};

funcionalidade.initEventos = function() {
	$("#btnCadastrar").on('click', function() {
		var dadosDoFormularioEstaoValidos = funcionalidade.validar();
		if (dadosDoFormularioEstaoValidos) {
			funcionalidade.gravar();
		}
	});
	$("#btnPesquisar").on('click', function() {
		var textoPesquisa = $("#nomePesquisa").val();
		funcionalidade.pesquisar(textoPesquisa);
	});
	$("#nomePesquisa").keyup(function() {
		var textoPesquisa = $("#nomePesquisa").val();
		funcionalidade.pesquisar(textoPesquisa);
	});
};

funcionalidade.gravar = function() {
	var classe = $("#classe").val();
	var metodo = $("#metodo").val();
	var nome = $("#nome").val();
	$.ajax(
			{
				url : url,
				async : ajaxAsyncPadrao,
				data : {
					classe : classe,
					metodo : metodo,
					nome : nome
				}
			}
		)
		.done(function(retorno){
			retorno = $.parseJSON(retorno);
			var consequencia = retorno.consequence;
			if(consequencia === "SUCCESSO") {
				var dado = retorno.dado;
				$("#tbl").append(funcionalidade.getLinhaTabelaFuncionalidade(dado));
			} else if (consequencia == "ERRO") {
				if (retorno.localizedMessage != null) {
					$("#mensagem").html(retorno.localizedMessage);
					$("#mensagem").addClass("erro");
					$("#mensagem").show();
				}
			} else if (consequencia == "ATENCAO") {
				if (retorno.dado != null) {
					$("#mensagem").html(retorno.dado);
					$("#mensagem").addClass("aviso");
					$("#mensagem").show();
				}
			} 
			log(retorno);
		})
		.fail(function(retorno){
			retorno = $.parseJSON(retorno);
			log(retorno);
		});
};

funcionalidade.validar = function() {
	var formValido = $("#nome").val() != '';
	return formValido;
};

funcionalidade.validarPesquisa = function() {
	var pesquisaValido = $("#nomePesquisa").val() != '';
	return pesquisaValido;
};

funcionalidade.preencherTabela = function(dado) {
	funcionalidade.resetTabelaFuncionalidade();
	for (var i = 0; i < dado.length; i++) {
		$("#tbl").append(funcionalidade.getLinhaTabelaFuncionalidade(dado[i]));
	}
	
	funcionalidade.atribuirEventosDeSelecaoAsLinhasDaTabela();
	ocultar($("#msgCarregando"));

	var quantidadeDeRegistros = $("tr[data-id]").length;
	if (quantidadeDeRegistros == 0) {
		ocultar($("#tbl.dados"));
		exibir($("#msgCarregando"));
	} else {
		exibir($("#tbl.dados"));
		ocultar($("#msgCarregando"));
	}
};

funcionalidade.listar = function() {
	exibir($("#msgCarregando"));
	ocultar($("#tbl.dados"));
	var classe = "Funcionalidade";
	var metodo = "listar";

	$.ajax(
		{
			url : url,
			async : ajaxAsyncPadrao,
			data : {
				classe : classe,
				metodo : metodo
			}
		}
	)
	.done(function(retorno){
		retorno = $.parseJSON(retorno);
		var consequencia = retorno.consequence;
		if(consequencia === "SUCCESSO") {
			var dado = retorno.dado;
			if (dado != null) {
				funcionalidade.preencherTabela(dado);
			}
		} else if (consequencia == "ERRO") {
			if (retorno.localizedMessage != null) {
				$("#mensagem").html(retorno.localizedMessage);
				$("#mensagem").addClass("erro");
				$("#mensagem").show();
			}
		} else if (consequencia == "ATENCAO") {
			if (retorno.dado != null) {
				$("#mensagem").html(retorno.dado);
				$("#mensagem").addClass("aviso");
				$("#mensagem").show();
			}
		} 
		console.log(retorno);
	})
	.fail(function(retorno){
		retorno = $.parseJSON(retorno);
		console.log(retorno);
	});
};

funcionalidade.pesquisar = function(textoPesquisa) {
	exibir($("#msgCarregando"));
	ocultar($("#tbl.dados"));
	var classe = "Funcionalidade";
	var metodo = "pesquisarPorNomeComo";
	$.ajax(
			{
				url : url,
				async : ajaxAsyncPadrao,
				data : {
					classe : classe,
					metodo : metodo,
					nome   : textoPesquisa
				}
			}
	)
	.done(function(retorno){
		retorno = $.parseJSON(retorno);
		var consequencia = retorno.consequence;
		if(consequencia === "SUCCESSO") {
			var dado = retorno.dado;
			if (existe(dado)) {
				funcionalidade.preencherTabela(dado);
			}
		} else if (consequencia == "ERRO") {
			if (retorno.localizedMessage != null) {
				$("#mensagem").html(retorno.localizedMessage);
				$("#mensagem").addClass("erro");
				$("#mensagem").show();
			}
		} else if (consequencia == "ATENCAO") {
			if (retorno.dado != null) {
				$("#mensagem").html(retorno.dado);
				$("#mensagem").addClass("aviso");
				$("#mensagem").show();
			}
		} 
		console.log(retorno);
	})
	.fail(function(retorno){
		retorno = $.parseJSON(retorno);
		console.log(retorno);
	});
};

funcionalidade.getLinhaTabelaFuncionalidade = function(dado) {
	var linha = "";
	linha = "<li class='ui-widget-content'>";
	linha = 	"<tr data-id="+dado.id+">";
	linha += 		"<td>";
	linha += 			dado.id;
	linha += 		"</td>";
	linha += 		"<td>";
	linha += 			dado.nome;
	linha += 		"</td>";
	linha += 		"<td>";
	linha += 			dado.acesso;
	linha += 		"</td>";
	linha += 		"<td>";
	if (existe(dado.modulo)) {
		linha += 			dado.nome;
	}
	linha += 		"</td>";
	linha += 	"</tr>";
	linha += "</li>";
	
	return linha;
};

funcionalidade.atribuirEventosDeSelecaoAsLinhasDaTabela = function() {
	$("tr[data-id]").on('click', function() {
		var id = $(this).data('id');
		funcionalidade.selecionarFuncionalidade(id);
	});
};

funcionalidade.selecionarFuncionalidade = function(id) {
	var classe = "Funcionalidade";
	var metodo = "pesquisar";

	$.ajax(
			{
				url : url,
				async : ajaxAsyncPadrao,
				data : {
					classe : classe,
					metodo : metodo,
					id : id
				}
			}
		)
		.done(function(retorno){
			retorno = $.parseJSON(retorno);
			var consequencia = retorno.consequence;
			if(consequencia === "SUCCESSO") {
				var funcionalidade = retorno.dado;
				if (funcionalidade != null) {
					$("#id").val(funcionalidade.id);
					$("#nome").val(funcionalidade.nome);
					$("#acesso").val(funcionalidade.acesso);
				}
			} else if (consequencia == "ERRO") {
				if (retorno.localizedMessage != null) {
					$("#mensagem").html(retorno.localizedMessage);
					$("#mensagem").addClass("erro");
					$("#mensagem").show();
				}
			} else if (consequencia == "ATENCAO") {
				if (retorno.dado != null) {
					$("#mensagem").html(retorno.dado);
					$("#mensagem").addClass("aviso");
					$("#mensagem").show();
				}
			} 
			console.log(retorno);
		})
		.fail(function(retorno){
			retorno = $.parseJSON(retorno);
			console.log(retorno);
		});
};

funcionalidade.resetTabelaFuncionalidade = function() {
	$("#tbl").html('');
	var tabelaInicial = "";
	
	tabelaInicial += "<thead>";
	tabelaInicial += 	"<tr>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Id";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Nome";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Acesso";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Módulo";
	tabelaInicial += 		"</td>";
	tabelaInicial += 	"</tr>";
	tabelaInicial += "</thead>";
	
	$("#tbl").html(tabelaInicial);
};