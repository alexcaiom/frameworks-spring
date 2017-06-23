/**
 * 
 */

var modulo = {};

modulo.init = function() {
	modulo.listar();
	modulo.initEventos();
};

modulo.initEventos = function() {
	$("#btnCadastrar").on('click', function() {
		var dadosDoFormularioEstaoValidos = modulo.validar();
		if (dadosDoFormularioEstaoValidos) {
			modulo.gravar();
		}
	});
	$("#btnPesquisar").on('click', function() {
		var textoPesquisa = $("#nomePesquisa").val();
		modulo.pesquisar(textoPesquisa);
	});
	$("#nomePesquisa").keyup(function() {
		var textoPesquisa = $("#nomePesquisa").val();
		modulo.pesquisar(textoPesquisa);
	});
};

modulo.gravar = function() {
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
				$("#tbl").append(modulo.getLinhaTabelaModulo(dado));
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

modulo.validar = function() {
	var formValido = $("#nome").val() != '';
	return formValido;
};

modulo.validarPesquisa = function() {
	var pesquisaValido = $("#nomePesquisa").val() != '';
	return pesquisaValido;
};


modulo.preencherTabela = function(dado) {
	modulo.resetTabelaModulo();
	for (var i = 0; i < dado.length; i++) {
		$("#tbl").append(modulo.getLinhaTabelaModulo(dado[i]));
	}
	
	modulo.atribuirEventosDeSelecaoAsLinhasDaTabela();
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

modulo.listar = function() {
	exibir($("#msgCarregando"));
	ocultar($("#tbl.dados"));
	var classe = "Modulo";
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
				modulo.preencherTabela(dado);
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

modulo.pesquisar = function(textoPesquisa) {
	exibir($("#msgCarregando"));
	ocultar($("#tbl.dados"));
	var classe = "Modulo";
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
				modulo.preencherTabela(dado);
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

modulo.getLinhaTabelaModulo = function(dado) {
	var linha = "";
	
	linha = "<tr data-id="+dado.id+">";
	linha += 	"<td>";
	linha += 		dado.id;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.nome;
	linha += 	"</td>";
	linha += 	"<td>";
	if (existe(dado.sistema) && existe(dado.sistema.nome)) {
		var sistema = dado.sistema;
		linha += 		sistema.nome;
	}
	linha += 	"</td>";
	linha += "</tr>";
	
	return linha;
};

modulo.atribuirEventosDeSelecaoAsLinhasDaTabela = function() {
	$("tr[data-id]").on('click', function() {
		var id = $(this).data('id');
		modulo.selecionarModulo(id);
	});
};

modulo.selecionarModulo = function(id) {
	var classe = "Modulo";
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
				var modulo = retorno.dado;
				if (modulo != null) {
					$("#id").val(modulo.id);
					$("#nome").val(modulo.nome);
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

modulo.resetTabelaModulo = function() {
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
	tabelaInicial += 			"Sistema";
	tabelaInicial += 		"</td>";
	tabelaInicial += 	"</tr>";
	tabelaInicial += "</thead>";
	
	$("#tbl").html(tabelaInicial);
};