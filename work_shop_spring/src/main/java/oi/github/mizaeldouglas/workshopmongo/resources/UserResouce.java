package oi.github.mizaeldouglas.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oi.github.mizaeldouglas.workshopmongo.domain.User;
import oi.github.mizaeldouglas.workshopmongo.dto.UserDTO;
import oi.github.mizaeldouglas.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResouce {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
		
		
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findId(@PathVariable String id) {
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
		
		
		
	}
	
}
