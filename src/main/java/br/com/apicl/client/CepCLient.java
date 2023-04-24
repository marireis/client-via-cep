package br.com.apicl.client;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import br.com.apicl.model.dto.EnderecoSaidaDto;
import br.com.apicl.util.JsonUtil;

public class CepCLient {

	//pega a url da api, onde {cep} será editavel
	private static final String URL = "https://viacep.com.br/ws/{cep}/json/";
	
	private JsonUtil json = new JsonUtil();
	
	public Client criar() {
		
		try {
			
			SSLContext sc = SSLContext.getInstance("ssl");
			sc.init(null, new X509TrustManager[] { new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, null);

			Client client = ClientBuilder.newBuilder().sslContext(sc).build();
			
			client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

			return client;
		} catch(Exception e) {
			throw new RuntimeException("Erro ao criar o Client");
		}
	}
	
	public EnderecoSaidaDto pegarUm(String cep) throws IOException {
		Client client = criar();
		
		WebTarget target = client.target(URL.replace("{cep}", cep)); //transforma {cep) na string passada
		
		Builder builder = target.request();
		
		Response response = builder.get();
		
		int status = response.getStatus(); 
		if (status == 200) {
			
			String jsonVindo = response.readEntity(String.class);

			System.out.println(jsonVindo); // devolve um json

			EnderecoSaidaDto dto = json.objeto(jsonVindo, EnderecoSaidaDto.class); // transforma o json em saidaDto

			return dto;
		}
		throw new RuntimeException("Status: " + status);//retorna exceçao caso não seja status 200 ou 201
	}

}
