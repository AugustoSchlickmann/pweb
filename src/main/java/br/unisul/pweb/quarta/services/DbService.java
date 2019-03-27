package br.unisul.pweb.quarta.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.pweb.quarta.domain.Categoria;
import br.unisul.pweb.quarta.domain.Estado;

import br.unisul.pweb.quarta.repositories.CategoriaRepository;
import br.unisul.pweb.quarta.repositories.EstadoRepository;

@Service
public class DbService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public void inicializaBancoDeDados() throws ParseException {
		
		Estado e1 = new Estado(null, "Rio Grande do Sul");
		Estado e2 = new Estado(null, "Santa Catarina");
		Estado e3 = new Estado(null, "Paraná");
		Estado e4 = new Estado(null, "São Paulo");
		Estado e5 = new Estado(null, "Rio de Janeiro");
		Estado e6 = new Estado(null, "Amazonas");
		Estado e7 = new Estado(null, "Sergipe");
		estadoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
	}

}
