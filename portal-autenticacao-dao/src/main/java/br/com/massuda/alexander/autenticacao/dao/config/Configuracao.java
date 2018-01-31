/**
 * 
 */
package br.com.massuda.alexander.autenticacao.dao.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.massuda.alexander.autenticacao.dao.IUsuarioDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.usuario.FinderUsuarioImpl;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.IFinderUsuario;
import br.com.massuda.alexander.autenticacao.dao.impl.UsuarioDAOImpl;
import br.com.massuda.alexander.autenticacao.dao.utils.ConstantesPersistencia;

/**
 * @author Alex
 *
 */
@Configuration
public class Configuracao {

	@Value("${libpersistencia.jdbc.keep-alive.timeout}")
	private Long tempoDeMonitoracaoDeBD;
	
	@Value("${libpersistencia.jdbc.keep-alive.query}")
	private String keepAliveQuery;
	
	@Bean
	public IUsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	@Bean
	public IFinderUsuario getFinderUsuario() {
		return new FinderUsuarioImpl();
	}
	

	@PostConstruct
	public void initBD () {
		ConstantesPersistencia.Monitoracao.INTERVALO_TEMPO = this.tempoDeMonitoracaoDeBD;
		ConstantesPersistencia.Monitoracao.query = this.keepAliveQuery;
	}
	
	public Long getTempoDeMonitoracaoDeBD() {
		return tempoDeMonitoracaoDeBD;
	}

	public void setTempoDeMonitoracaoDeBD(Long tempoDeMonitoracaoDeBD) {
		this.tempoDeMonitoracaoDeBD = tempoDeMonitoracaoDeBD;
	}
	
}
