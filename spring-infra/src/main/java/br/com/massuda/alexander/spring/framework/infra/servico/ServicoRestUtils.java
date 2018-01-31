/**
 * 
 */
package br.com.massuda.alexander.spring.framework.infra.servico;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import br.com.waiso.framework.exceptions.ErroNegocio;

/**
 * @author Alex
 *
 */
public class ServicoRestUtils {

	/**
	 * isError
	 * @param resposta
	 * @return
	 */
	protected boolean ehErro(Response resposta) {
		StatusType statusInfo = resposta.getStatusInfo();
		HttpStatus statusHTTP = HttpStatus.valueOf(statusInfo.getStatusCode());
		return statusHTTP.is4xxClientError() || statusHTTP.is5xxServerError();
	}
	
	/**
	 * Treat errors
	 * @param resposta
	 */
	protected void tratarErro(Response resposta) {
		HttpStatus statusCode = HttpStatus.valueOf(resposta.getStatus());
		String mensagemServidor = resposta.readEntity(String.class);
		switch (statusCode.series()) {
			case CLIENT_ERROR:
				if (StringUtils.isEmpty(mensagemServidor)) {
					throw new HttpClientErrorException(statusCode,  resposta.getStatusInfo().getReasonPhrase());
				} else {
					throw new ErroNegocio(mensagemServidor);
				}
			case SERVER_ERROR:
				throw new HttpServerErrorException(statusCode, StringUtils.isEmpty(mensagemServidor) ? resposta.getStatusInfo().getReasonPhrase() : mensagemServidor);
			default:
				throw new RestClientException("Unknown status code [" + statusCode + "]");
		}
	}

}
