package oi.github.mizaeldouglas.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oi.github.mizaeldouglas.workshopmongo.domain.Post;
import oi.github.mizaeldouglas.workshopmongo.domain.User;
import oi.github.mizaeldouglas.workshopmongo.repository.PostRepository;
import oi.github.mizaeldouglas.workshopmongo.services.exception.ObjectNotFoundExeception;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado"));
	}

	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		
		maxDate = new Date(maxDate.getDate() +  24 * 60 * 60 * 1000);
		
		return repo.fullSearch(text, minDate, maxDate);
		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}