package br.com.massuda.alexander.autenticacao.dao.utils;

public class ConstantesPersistencia {
	public static final String BANCO_DE_DADOS_LOCAL = "alexcaiom.com.br";
		public static final String BANCO_DE_DADOS_CONEXAO_USUARIO = "alexcaio";
		public static final String BANCO_DE_DADOS_CONEXAO_SENHA = "lifE2sg@3ood";
		public static final String BANCO_DE_DADOS_NOME = "alexcaio_"+br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_NOME_BD;
//		public static final String BANCO_DE_DADOS_LOCAL = "localhost";
//		public static final String BANCO_DE_DADOS_CONEXAO_USUARIO = "root";
//		public static final String BANCO_DE_DADOS_CONEXAO_SENHA = "lifesgood";
		
		public static final class Monitoracao {
			public static boolean ativa = true;
			public static long INTERVALO_TEMPO = 0l;
			public static String query = "";
		}
		
		
		public static boolean BANCO_DE_DADOS_POOL_CONEXOES_ATIVO = false;
//		public static final String BANCO_DE_DADOS_NOME = br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_NOME_BD;
}

