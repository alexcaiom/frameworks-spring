package br.com.massuda.alexander.autenticacao.dao.impl;

import br.com.massuda.alexander.persistencia.jdbc.DAOGenericoJDBCImpl;

public abstract class DAO<T> extends DAOGenericoJDBCImpl<T> {

	public DAO(Class<T> entidade) {
		super(entidade);
		configurarBancoDeDadosDadosAcesso();
	}

	/**
	 * 
	 */
	public static void configurarBancoDeDadosDadosAcesso() {
		br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_LOCAL = br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_LOCAL;
		br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_USUARIO = br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_CONEXAO_USUARIO;
		br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_SENHA = br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_CONEXAO_SENHA;
		br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BD_CONEXAO_NOME_BD = br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_NOME;
		
		
		if (br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_POOL_CONEXOES_ATIVO) {
			br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia.BANCO_DE_DADOS_POOL_CONEXOES_ATIVO = 
					br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia.BANCO_DE_DADOS_POOL_CONEXOES_ATIVO;
		}
	}
	
}
