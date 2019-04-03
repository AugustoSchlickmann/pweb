package br.unisul.pweb.quarta.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.pweb.quarta.domain.Cidade;
import br.unisul.pweb.quarta.dtos.CidadeDTO;
import br.unisul.pweb.quarta.services.CidadeService;


@RestController //trafegar informaçao no padrao Rest, em formato json, entre chaves igual ao mod do mh
@RequestMapping(value="/cidades") //localhost:8080/cidades
public class CidadeResource {


	@Autowired
	private CidadeService service; //objeto do tipo CidadeService
	
	//BUSCAR POR ID localhost8080/Cidades/id  ResponseEntity é o retorno do metodo que no caso é uma Cidade 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id){
		Cidade obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//INSERIR post é salvar uma nova Cidade 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Cidade obj){
		obj = service.insert(obj);
		//pega caminho atual e ve o objeto que foi inserido no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//ATUALIZAR
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cidade obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	
	//EXCLUIR
	//passa pela URL o id do Cidade, entre chaves pois é um parametro
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();  
	}
	
	//LISTAR TODAS
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> lista = service.findAll();
		List<CidadeDTO> listaDTO = new ArrayList<CidadeDTO>();
		for (Cidade e : lista) {
			listaDTO.add(new CidadeDTO(e));
		}
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
}
