package br.unisul.pweb.quarta.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.pweb.quarta.domain.Cidade;
import br.unisul.pweb.quarta.domain.Estado;
import br.unisul.pweb.quarta.dtos.CidadeDTO;
import br.unisul.pweb.quarta.dtos.EstadoDTO;
import br.unisul.pweb.quarta.services.CidadeService;
import br.unisul.pweb.quarta.services.EstadoService;

@RestController //trafegar informaçao no padrao Rest, em formato json, entre chaves igual ao mod do mh
@RequestMapping(value="/estados") //localhost:8080/estados
public class EstadoResource {


	@Autowired
	private EstadoService service; //objeto do tipo EstadoService
	
	@Autowired
	private CidadeService cidadeService;
	
	//BUSCAR POR ID localhost8080/Estados/id  ResponseEntity é o retorno do metodo que no caso é uma Estado 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Integer id){
		Estado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//INSERIR post é salvar uma nova Estado 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Estado obj){
		obj = service.insert(obj);
		//pega caminho atual e ve o objeto que foi inserido no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//ATUALIZAR
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Estado obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	
	//EXCLUIR
	//passa pela URL o id do estado, entre chaves pois é um parametro
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();  
	}
	
	//LISTAR TODAS
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> lista = service.findAll();
		//List<EstadoDTO> listaDTO = lista.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList()); ou foreach para percorrer a lista 
		List<EstadoDTO> listaDTO = new ArrayList<EstadoDTO>();
		for (Estado e : lista) {
			listaDTO.add(new EstadoDTO(e));
		}
		return ResponseEntity.ok().body(listaDTO);
	}
	
	//LISTAR CIDADES DE UM ESTADO
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}