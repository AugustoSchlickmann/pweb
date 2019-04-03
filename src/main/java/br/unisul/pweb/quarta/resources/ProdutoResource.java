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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.pweb.quarta.domain.Produto;
import br.unisul.pweb.quarta.dtos.ProdutoDTO;
import br.unisul.pweb.quarta.resources.utils.URL;
import br.unisul.pweb.quarta.services.ProdutoService;


	@RestController
	@RequestMapping(value="/produtos") //localhost:8080/Produtos
	
	public class ProdutoResource {


		@Autowired
		private ProdutoService service; //objeto do tipo ProdutoService
		
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public ResponseEntity<Produto> find(@PathVariable Integer id) {
			Produto obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ProdutoDTO>> find(
				@RequestParam(value = "nome", defaultValue = "") String nome,
				@RequestParam(value = "categorias", defaultValue = "") String categorias) {

			String nomeDecoded = URL.decodeParam(nome);
			List<Integer> ids = URL.decodeIntList(categorias);
			List<Produto> list = service.search(nomeDecoded, ids);
			List<ProdutoDTO> listDto = new ArrayList<ProdutoDTO>();
			for (Produto p : list) {
				listDto.add(new ProdutoDTO(p));
			}
			return ResponseEntity.ok().body(listDto);

		}
		
		//INSERIR post é salvar uma nova Produto 
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void>insert(@RequestBody Produto obj){
			obj = service.insert(obj);
			//pega caminho atual e ve o objeto que foi inserido no banco
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}

		//ATUALIZAR
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@RequestBody Produto obj, @PathVariable Integer id){
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}

		
		//EXCLUIR
		//passa pela URL o id do Produto, entre chaves pois é um parametro
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();  
		}
		
		
}
