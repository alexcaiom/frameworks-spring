package br.com.massuda.alexander.spring.framework.infra.servico;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class Servico<T> extends ServicoRestUtils {

//	@Autowired
//	protected RestConector<T> restConector;
	protected WebTarget target;
	protected Client client = ClientBuilder.newClient();
	protected Class<T> tipo;
	protected String entidade = "";
	
	@PostConstruct
	public void setNomeEntidade () {
//		TypeVariable<?>[] typeParameters = getParametroDeTipoCasoHaja();
//		if (typeParameters != null && typeParameters.length > 0) {
//			this.entidade = typeParameters[0].getName();
//		}
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
		String typeName = actualTypeArgument.getTypeName();
		if (typeName.contains(".")) {
			String[] partes = typeName.split("[.]");
			if (partes.length>0) {
				typeName = partes[partes.length-1];
			}
		}
		this.entidade = typeName.toLowerCase();
	}
	
	private TypeVariable<?>[] getParametroDeTipoCasoHaja() {
		TypeVariable<?>[] typeParameters = this.getClass().getTypeParameters();
		boolean temHeranca = !this.getClass().getSuperclass().equals(Object.class);
		boolean temParametros = typeParameters.length > 0;
		if (!temParametros && temHeranca) {
			Class mae = this.getClass().getSuperclass();
			typeParameters = mae.getTypeParameters();
		}
		return typeParameters;
	}
	
	protected T getEntity (Response resposta) {
		tratarPossiveisErros(resposta);
		return resposta.readEntity(tipo);
	}
	
	protected List<T> getEntityList (Response resposta) {
		tratarPossiveisErros(resposta);
		ParameterizedType parameterizedGenericType = new ParameterizedType() {
	        public Type[] getActualTypeArguments() {
	            return new Type[] { tipo };
	        }

	        public Type getRawType() {
	            return List.class;
	        }

	        public Type getOwnerType() {
	            return List.class;
	        }
	    };

	    GenericType<List<T>> tipoGenerico = new GenericType<List<T>>(
	            parameterizedGenericType) {
	    };
		return resposta.readEntity(tipoGenerico);
	}

	/**
	 * In case of having errors we shall treat them
	 * throwing a corresponding Exception
	 * 
	 * @param resposta / response
	 */
	private void tratarPossiveisErros(Response resposta) {
		if (ehErro(resposta)) {
			tratarErro(resposta);
		}
	}
}
