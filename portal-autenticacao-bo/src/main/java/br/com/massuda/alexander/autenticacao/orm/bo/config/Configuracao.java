/**
 * 
 */
package br.com.massuda.alexander.autenticacao.orm.bo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.massuda.alexander.autenticacao.orm.bo.BOUsuario;
import br.com.massuda.alexander.autenticacao.orm.bo.BOUsuarioImpl;

/**
 * @author Alex
 *
 */
@Import({br.com.massuda.alexander.autenticacao.dao.config.Configuracao.class})
@Configuration
public class Configuracao {

	@Bean
	public BOUsuario getBOUsuarioImpl() {
		return new BOUsuarioImpl();
	}
}
