package br.com.massuda.alexander.spring.framework.infra.excecoes.tratamento;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import br.com.massuda.alexander.spring.framework.infra.excecoes.Erro;

@ControllerAdvice
public class TratadorDeExcecoes {
	
	private final Logger LOGGER = LogManager.getLogger(getClass());

	@ExceptionHandler({RestClientException.class, HttpServerErrorException.class})
	public void tratarErroHTTP(RestClientException e) {
		Erro erro = new Erro(e, e.getMessage());
		throw erro;
	}
	
	@ExceptionHandler(Erro.class)
	public ResponseEntity<String> tratarErroHTTP(Erro e) {
		LOGGER.error("Erro:", e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
