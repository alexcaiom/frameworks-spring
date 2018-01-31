//package br.com.massuda.alexander.spring.framework.infra.servico;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.ResponseErrorHandler;
//
//import br.com.massuda.alexander.spring.framework.infra.excecoes.Erro;
//import br.com.massuda.alexander.spring.framework.infra.servico.utils.RestTemplate;
//
//@Component
//public class RestConector<T>  {
//
//	@Autowired
//	private RestTemplate restTemplate;
//	private Class tipo;
//	
//	/**
//	 * Acesso via GET
//	 * @param url
//	 * @return
//	 */
//	public T getForEntity(String url) {
//		ResponseEntity<T> resposta = restTemplate.getForEntity(url, tipo);
//		return tratarResposta(resposta);
//	}
//	
//	/**
//	 * 
//	 * @param url
//	 * @param tipo
//	 * @return
//	 */
//	public T getForEntity(String url, Class tipo) {
//		ResponseEntity<T> resposta = restTemplate.getForEntity(url, tipo);
//		return tratarResposta(resposta);
//	}
//	
//	public T getForObject(String url) {
//		 T list = (T) restTemplate.getForObject(url, tipo);
//		return list;
//	}
//	
//	public T getForObject(String url, Class tipo) {
//		T obj = (T) restTemplate.getForObject(url, tipo);
//		return obj;
//	}
//	
//	/**
//	 * 
//	 * @param url
//	 * @param o
//	 * @param tipo
//	 * @return
//	 */
//	public T postForEntity(String url, T o, Class tipo) {
//		ResponseEntity<T> resposta = restTemplate.postForEntity(url, o, tipo);
//		return tratarResposta(resposta);
//	}
//	
//	/**
//	 * 
//	 * @param url
//	 * @param o
//	 * @return
//	 */
//	public T postForEntity(String url, T o) {
//		ResponseEntity<T> resposta = restTemplate.postForEntity(url, o, tipo);
//		return tratarResposta(resposta);
//	}
//
//	private boolean sucesso(ResponseEntity<T> resposta) {
//		return resposta.getStatusCode().is2xxSuccessful() ||
//				resposta.getStatusCode().is1xxInformational();
//	}
//	
//	private T tratarResposta(ResponseEntity<T> resposta) {
//		if (!sucesso(resposta)) {
//			tratarHttpStatusErro(resposta);
//		}
//		return resposta.getBody();
//	}
//	
//	private void tratarHttpStatusErro(ResponseEntity<T> resposta) {
//		Erro erro = (Erro) resposta.getBody();
//		throw erro;
//	}
//	
//	
//}
