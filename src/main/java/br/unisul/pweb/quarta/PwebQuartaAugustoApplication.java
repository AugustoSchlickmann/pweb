package br.unisul.pweb.quarta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//domain - entidades, mapeia para o banco de dados
//dtos - representação dos objetos
//repositories - interfaces
//services - tudo que tem acesso ao banco de dados, fazem a manipulaçao do banco de dados
//resources - porta de acesso dos serviços, URL , chamada dos serviços pelo url
//config - configuraços diversas ex: conectar mysql para desenvolvimento
//JPA - API do java que faz persistencia no banco de dados

@SpringBootApplication
public class PwebQuartaAugustoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwebQuartaAugustoApplication.class, args);
	}

}
//relaçao entre categoria e produtos é muito pra muitos simples, uma categoria tem varios produtos e um produto pode ter mais de
//uma categoria

//list é pai do arraylist

//cancelar a busca ciclica na pesquisa do produto, quando o cliente acha o produto nao interessa para ele saber que tal produto faz
//parte das categorias x , y z

//decodificar as urls que chegam para mim, tirar os % os ++ etc URLDecoder.decode

//HashSet nao permite cadastros duplicados

//FRONT END EM ANGULAR

//***EXPLICAÇÃO DA TABELA ASSOCIATIVA ITEM_PEDIDO***
//
// 
//