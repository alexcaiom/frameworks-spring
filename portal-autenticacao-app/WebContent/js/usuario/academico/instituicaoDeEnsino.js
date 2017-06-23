/**
 * 
 */

var instituicao = {};

instituicao.init = function() {
	instituicao.preencherTabela();
};

instituicao.preencherTabela = function() {
	var classe = "InstituicaoDeEnsino";
	var metodo = "listar";

	$.ajax(
		{
			url : url,
			async : false,
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
				instituicao.resetTabelaInstituicao();
				for (var i = 0; i < dado.length; i++) {
					$("#tbl").append(instituicao.getLinhaTabelaInstituicao(dado[i]));
				}
				
				instituicao.atribuirEventosDeSelecaoAsLinhasDaTabela();
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

instituicao.getLinhaTabelaInstituicao = function(dado) {
	var linha = "";
	
	linha = "<tr data-id="+dado.id+">";
	linha += 	"<td>";
	linha += 		dado.id;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.nome;
	linha += 	"</td>";
	linha += 	"<td>";
	if (existe(dado.endereco) && existe(dado.endereco.logradouro)) {
		linha += 		dado.endereco.logradouro;
	}
	linha += 	"</td>";
	linha += "</tr>";
	
	return linha;
};

instituicao.atribuirEventosDeSelecaoAsLinhasDaTabela = function() {
	$("tr[data-id]").on('click', function() {
		var id = $(this).data('id');
		instituicao.selecionarInstituicao(id);
	});
};

instituicao.selecionarInstituicao = function(id) {
	var classe = "Instituicao";
	var metodo = "pesquisar";

	$.ajax(
			{
				url : url,
				async : false,
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
				var instituicao = retorno.dado;
				if (instituicao != null) {
					$("#id").val(instituicao.id);
					$("#nome").val(instituicao.nome);
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

instituicao.resetTabelaInstituicao = function() {
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
	tabelaInicial += 			"Endereço";
	tabelaInicial += 		"</td>";
	tabelaInicial += 	"</tr>";
	tabelaInicial += "</thead>";
	
	$("#tbl").html(tabelaInicial);
};