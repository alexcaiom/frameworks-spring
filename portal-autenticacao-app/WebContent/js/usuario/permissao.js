/**
  * Cadastro de Usuario
  */

var usuario = {};

usuario.init = function() {
	usuario.preencherTabela();
};

usuario.preencherTabela = function() {
	var classe = "Usuario";
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
				usuario.resetTabelaUsuario();
				for (var i = 0; i < dado.length; i++) {
					$("#tbl").append(usuario.getLinhaTabelaUsuario(dado[i]));
				}
				
				usuario.atribuirEventosDeSelecaoAsLinhasDaTabela();
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

usuario.getLinhaTabelaUsuario = function(dado) {
	var linha = "";
	
	linha = "<tr data-id="+dado.id+">";
	linha += 	"<td>";
	linha += 		dado.id;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.nome;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.login;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		dado.email;
	linha += 	"</td>";
	linha += 	"<td>";
	linha += 		getValorValido(dado.dataDeNascimento);
	linha += 	"</td>";
	linha += 	"<td>";
	if (existe(dado.perfil) && existe(dado.perfil.nome)) {
		var perfil = dado.perfil;
		linha += 		perfil.nome;
	}
	linha += 	"</td>";
	linha += 	"<td>";
	if (existe(dado.perfil) && existe(dado.perfil) && existe(dado.perfil.nivel)) {
		var perfil = dado.perfil;
		linha += 		perfil.nivel;
	}
	linha += 	"</td>";
	linha += "</tr>";
	
	return linha;
};

usuario.atribuirEventosDeSelecaoAsLinhasDaTabela = function() {
	$("tr[data-id]").on('click', function() {
		var id = $(this).data('id');
		usuario.selecionarUsuario(id);
	});
};

usuario.selecionarUsuario = function(id) {
	var classe = "Usuario";
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
				var usuario = retorno.dado;
				if (usuario != null) {
					$("#id").val(usuario.id);
					$("#nome").val(usuario.nome);
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

usuario.resetTabelaUsuario = function() {
	$("#tbl").html('');
	var tabelaInicial = "";
	
	tabelaInicial += "<thead>";
	tabelaInicial += 	"<tr bordercolor='black' border='2px'>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Id";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Nome";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Login";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"E-mail";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Data de Nascimento";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Perfil";
	tabelaInicial += 		"</td>";
	tabelaInicial += 		"<td>";
	tabelaInicial += 			"Nível do Perfil";
	tabelaInicial += 		"</td>";
	tabelaInicial += 	"</tr>";
	tabelaInicial += "</thead>";
	
	$("#tbl").html(tabelaInicial);
};