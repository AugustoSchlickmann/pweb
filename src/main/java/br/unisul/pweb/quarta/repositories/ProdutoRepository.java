package br.unisul.pweb.quarta.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.unisul.pweb.quarta.domain.Categoria;
import br.unisul.pweb.quarta.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	//esse metodo facilita os codigos em sql para pesquisas
	List<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias);
}
