/**
 * 
 */

var login = {};



login.init = function () {
	/**
	 * Tratamentos de Login
	 */
	$("#txtLogin").on('focus', function(){
		if($("#login").val() === "")
			$("#txtLogin").val('');
	});
	$("#txtLogin").on('blur', function(){
		if($("#login").val() === "")
			$("#txtLogin").val('login');
	});
	$("#txtLogin").on('change', function(){
		var textoLogin = $("#txtLogin").val(); 
		$("#login").val(textoLogin);
		
		$("#mensagem").html('');
		$("#mensagem").removeClass("aviso");
		$("#mensagem").removeClass("erro");
		$("#mensagem").hide();
	});

	/**
	 * Tratamentos de Senha
	 */
	$("#txtSenha").on('focus', function(){
		if($("#senha").val() === ""){
			$("#txtSenha").val('');
		}
		$("#txtSenha").attr('type', 'password');
	});
	$("#txtSenha").on('blur', function(){
		if($("#senha").val() === ""){
			$("#txtSenha").attr('type', 'text');
			$("#txtSenha").val('senha');
		}
	});
	$("#txtSenha").on('change', function(){
		var textoLogin = $("#txtSenha").val(); 
		$("#senha").val(textoLogin);
	});
	
	$("#txtSenha").on('keypress', function(event){
		var teclaEnter = 13;
		var codigoTecla = event.which;
		if (codigoTecla === teclaEnter) {
			$("#btnEntrar").click();
		}
	});
	
	$("#btnEntrar").on('click', function(){
		var classe = $("#classe").val();
		var metodo = $("#metodo").val();
		var login = $("#login").val();
		var senha = $("#senha").val();
		
		$.ajax(
			{
				url : url,
				async : false,
				data : {
					classe : classe,
					metodo : metodo,
					login : login,
					senha : senha,
				}
			}
		)
		.done(function(retorno){
			retorno = $.parseJSON(retorno);
			var consequencia = retorno.consequence;
			if(consequencia === "SUCCESSO") {
				var dado = retorno.dado;
				var usuario = dado;
				console.log(usuario);
				
				$("#frmLogin").fadeOut();
				$("#areaUsuarioLogado").fadeIn();
				$("#lblLogin").html(usuario.nome);
				
				usuarioLogado = true;
				exibirUsuario();
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
		
	});
	
};

login.selecionaTelaASerExibida = function () {
	var classe = "Usuario";
	var metodo = "usuarioEstaLogado";
	var login = "{login}";

	$.ajax(
		{
			url : url,
			async : false,
			data : {
				classe : classe,
				metodo : metodo,
				login : login
			}
		}
	)
	.done(function(retorno){
		retorno = $.parseJSON(retorno);
		var consequencia = retorno.consequence;
		if(consequencia === "SUCCESSO") {
			var dado = retorno.dado;
			var usuario = dado;
			console.log(usuario);
			
			$("#frmLogin").fadeOut();
			$("#areaUsuarioLogado").fadeIn();
			$("#lblLogin").html(usuario.nome);
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

login.organizaLogin = function() {
	var nomeDoUsuario = "Alex";
	$("#lblLogin").val(nomeDoUsuario);
};

login.init();