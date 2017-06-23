package br.com.massuda.alexander.autenticacao.dao.finder.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.IFinderSistema;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Sistema;
import br.com.massuda.alexander.persistencia.jdbc.utils.TipoOperacao;

@Component
public class FinderSistemaImpl extends Finder<Sistema> implements IFinderSistema {

	public FinderSistemaImpl() {
		this(TipoOperacao.NORMAL);
	}
	
	public FinderSistemaImpl(TipoOperacao tipo) {
		super(Sistema.class);
		this.tipoOperacao = tipo;
	}

	public Sistema pesquisar(Long id) {
		Sistema s = null;
		s = super.pesquisar(id);
		return s;
	}
	
	public List<Sistema> pesquisarPorNomeComo(String nome) {
		List<Sistema> sistemas = new ArrayList<Sistema>();
		sistemas = super.pesquisarPorNomeComo(nome);
		return sistemas;
	}

	public List<Sistema> listar() {
		List<Sistema> sistemas = new ArrayList<Sistema>();
		sistemas = super.listar();
		return sistemas;
	}

	public void preencher(Sistema o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
