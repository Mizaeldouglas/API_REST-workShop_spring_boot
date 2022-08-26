package oi.github.mizaeldouglas.workshopmongo.resources.exeption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import oi.github.mizaeldouglas.workshopmongo.services.exception.ObjectNotFoundExeception;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundExeception.class)
	public ResponseEntity<StandardError> objectNotfound(ObjectNotFoundExeception e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;		
		StandardError err = new StandardError
				(System.currentTimeMillis(),status.value(),"Não encontado",e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
}
