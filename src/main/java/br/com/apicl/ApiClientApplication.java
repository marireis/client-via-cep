package br.com.apicl;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.apicl.client.CepCLient;

@SpringBootApplication
public class ApiClientApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ApiClientApplication.class, args);
		

		CepCLient cepClient = new CepCLient();
		
		System.out.println(cepClient.pegarUm("64001260"));
	}

}
