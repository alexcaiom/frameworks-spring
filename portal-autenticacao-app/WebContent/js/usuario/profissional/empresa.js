/**
 * 
 */

var sistema = {};

sistema.init = function() {
	sistema.preencherTabela();
};

sistema.preencherTabela = function() {
	var classe = "Sistema";
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
				sistema.resetTabelaSistema();
				for (var i = 0; i < dado.length; i++) {
					$("#tbl").append(sistema.getLinhaTabelaSistema(dado[i]));
				}
				
				sistema.atribuirEventosDeSelecaoAsLinhasDaTabela();
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

sistema.getLinhaTabelaSistema = function(dado) {
	var linha = "";
	
	linha = "<tr data-id="+dado.id+">";
	linha += 	"<td>";
	linha += 		dado.id;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.nome;
	linha += 	"</td>";
	linha += "</tr>";
	
	return linha;
};

sistema.atribuirEventosDeSelecaoAsLinhasDaTabela = function() {
	$("tr[data-id]").on('click', function() {
		var id = $(this).data('id');
		sistema.selecionarSistema(id);
	});
};

sistema.selecionarSistema = function(id) {
	var classe = "Sistema";
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
				var sistema = retorno.dado;
				if (sistema != null) {
					$("#id").val(sistema.id);
					$("#nome").val(sistema.nome);
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

sistema.resetTabelaSistema = function() {
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
	tabelaInicial += 	"</tr>";
	tabelaInicial += "</thead>";
	
	$("#tbl").html(tabelaInicial);
};