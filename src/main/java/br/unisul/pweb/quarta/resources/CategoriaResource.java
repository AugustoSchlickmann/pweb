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

import br.unisul.pweb.quarta.domain.Categoria;
import br.unisul.pweb.quarta.dtos.CategoriaDTO;
import br.unisul.pweb.quarta.services.CategoriaService;

@RestController //trafegar informaçao no padrao Rest
@RequestMapping(value="/categorias") //localhost:8080/categorias
public class CategoriaResource {

//	@RequestMapping(method=RequestMethod.GET)
//	public List<Categoria> listar(){
//		List<Categoria> lista = new ArrayList<Categoria>();
//		lista.add(new Categoria(1, "Informática"));
//		lista.add(new Categoria(2, "Camping"));
//		lista.add(new Categoria(3, "Automobilística"));
//		lista.add(new Categoria(4, "Lazer"));
//		return lista;
//	}

	@Autowired
	private CategoriaService service; //objeto do tipo CategoriaService
	
	//BUSCAR POR ID localhost8080/categorias/id  ResponseEntity é o retorno do metodo que no caso é uma categoria 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//INSERIR post é salvar uma nova categoria 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		//pega caminho atual e ve o objeto que foi inserido no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//ATUALIZAR
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	
	//EXCLUIR
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();  
	}
	
	//LISTAR TODAS
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> lista = service.findAll();
		//List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); ou foreach para percorrer a lista 
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
		for (Categoria c : lista) {
			listaDTO.add(new CategoriaDTO(c));
		}
		return ResponseEntity.ok().body(listaDTO);
	}
}