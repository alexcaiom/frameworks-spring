/**
  * Cadastro de Usuario
  */

var usuario = {};

usuario.init = function() {
	usuario.listar();
	usuario.initEventos();
};


usuario.initEventos = function() {
	var metodo = $("#metodo").val();
	if (metodo === 'incluir') {
		$("#campoNovaSenha").hide();
		$("#campoConfirmarNovaSenha").hide();
	} else if (metodo === 'editar') {
		$("#campoSenha").hide();
	}
	$("#btnCadastrar").on('click', function() {
		var dadosDoFormularioEstaoValidos = usuario.validar();
		if (dadosDoFormularioEstaoValidos) {
			usuario.gravar();
		}
	});
	$("#btnPesquisar").on('click', function() {
		var textoPesquisa = $("#nomePesquisa").val();
		usuario.pesquisar(textoPesquisa);
	});
	$("#nomePesquisa").on('keyrelease', function() {
		var textoPesquisa = $("#nomePesquisa").val();
		usuario.pesquisar(textoPesquisa);
	});
	
	$('input[name=endereco]').liveSearch(
			{
				url: 'http://maps.googleapis.com/maps/api/geocode/json?address='
			}
		);
};

usuario.gravar = function() {
	var classe = $("#classe").val();
	var metodo = $("#metodo").val();
	var nome = $("#nome").val();
	$.ajax(
			{
				url : url,
				async : false,
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
				$("#tbl").append(usuario.getLinhaTabelaUsuario(dado));
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

usuario.validar = function() {
	var formValido = $("#nome").val() != '';
	return formValido;
};

usuario.validarPesquisa = function() {
	var pesquisaValido = $("#nomePesquisa").val() != '';
	return pesquisaValido;
};

usuario.preencherTabela = function(dado) {
	usuario.resetTabelaUsuario();
	for (var i = 0; i < dado.length; i++) {
		$("#tbl").append(usuario.getLinhaTabelaUsuario(dado[i]));
	}
	
	usuario.atribuirEventosDeSelecaoAsLinhasDaTabela();
};

usuario.listar = function() {
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
				usuario.preencherTabela(dado);
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


usuario.pesquisar = function(textoPesquisa) {
	var classe = "Usuario";
	var metodo = "pesquisarPorNome";
	$.ajax(
			{
				url : url,
				async : false,
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
				usuario.preencherTabela(dado);
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