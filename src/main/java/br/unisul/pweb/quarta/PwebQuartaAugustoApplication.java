package br.unisul.pweb.quarta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//domain - entidades, mapeia para o banco de dados
//dtos - representação dos objetos
//repositories - interfaces
//services - tudo que tem acesso ao banco de dados
//resources - porta de acesso 
//config - configuraços diversas ex: conectar mysql para desenvolvimento
//JPA - API do java que faz persistencia no banco de dados

@SpringBootApplication
public class PwebQuartaAugustoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwebQuartaAugustoApplication.class, args);
	}

}
