package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.ILibroService;
import com.example.demo.interfaces.ILibro;
import com.example.demo.modelo.Libro;

@Service
public class LibroService implements ILibroService{

	@Autowired
	private ILibro data;
	
	// modifique el codigo para la us de bsuquedad
	// Proposito: si no recibe una keyword, muestra todos los libros existentes.
	// si recibe una keyword, muestra los libros que cumplan con la keyword
	/*
	@Override
	public List<Libro> listarTodos(String keyword) {
		if (keyword != null) {
			return data.findAll(keyword);
		}
		return (List<Libro>)data.findAll();
	}
	*/
	@Override
	public Page<Libro> listarTodos(Pageable pageable, String keyword) {
		if (keyword != null) {
			return (Page<Libro>)data.findAll(pageable,keyword);
		}
		return (Page<Libro>)data.findAll(pageable);
	}	
	
	@Override
	public Optional<Libro> listarPorId(int id) {
		Optional<Libro> libro = data.findById(id) ;
		if ( libro.isPresent() ) {
			return libro ;
		}
		return null ;
	}
	
	
	@Override
	public List<Libro> listarPorTitulo(String titulo) {
		List<Libro> librosMismoTitulo = data.findByTitulo(titulo) ;
		return librosMismoTitulo ;
	}
	
	
	
	@Override
	public List<Libro> listarPorGenero(String genero) {
		List<Libro> librosMismoTitulo = data.findByGenero(genero) ;
		return librosMismoTitulo ;
	}
	
	
	@Override
	public List<Libro> listarPorAutor(String autor) {
		List<Libro> librosMismoAutor = data.findByAutor(autor) ;
		return librosMismoAutor ;
	}

	

	@Override
	public int save(Libro libro) {
		int res= 0;
		Libro l= data.save(libro);
		if(!l.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void delete(int isbn) {
		data.deleteById(isbn);
	}

	@Override
	public List<Libro> listarLibros() {
		return (List<Libro>)data.findAll();
	}


	
}
