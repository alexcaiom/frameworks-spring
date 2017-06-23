<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript" src="js/all.js"></script>
</head>
<body>

	<fieldset id="frmLogin">
		<legend>Login</legend>
		<input type="hidden" id="classe" value="Usuario" />
		<input type="hidden" id="metodo" value="autenticar" />
		<input type="text" name="login" id="txtLogin" value="login" title="login" />
		<input type="text" name="senha" id="txtSenha" value="senha" title="senha" style="margin-left: 5px;" />
		<input type="button" id="btnEntrar" value="entrar" title="entrar" />
		<input type="hidden" id="login" />
		<input type="hidden" id="senha" /><br/>
		
		<span id="mensagem" class="mensagem"></span>
	</fieldset>
	
	<label id="areaUsuarioLogado">
		<span id="lblLogin"></span>
		<span id="dadosLogin"></span>
	</label>

</body>
<script type="text/javascript" src="js/login.js"></script>
</html>