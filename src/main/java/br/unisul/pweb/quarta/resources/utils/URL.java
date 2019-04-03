package br.unisul.pweb.quarta.resources.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	//classe estatica
	
	//java tem um metodo chamado URLDecoder
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
		String[] vetor = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vetor.length; i++) {
			list.add(Integer.parseInt(vetor[i]));
		}
		return list;
	}
}
