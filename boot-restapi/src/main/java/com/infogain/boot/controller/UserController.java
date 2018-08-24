package com.infogain.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.boot.entity.Users;
import com.infogain.boot.services.IGenericService;

@RestController 
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IGenericService<Users> iGenericServicUsers;

	@GetMapping() 
	public ResponseEntity<?> getUsersDetails(Users users) {
		List<Users> listUsers = iGenericServicUsers.fetchAll(users);
		if (listUsers.isEmpty()) {
			return new ResponseEntity<>("NO user details found!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listUsers, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> getUserById(Users user,@PathVariable String id) { 
		try {
			user = iGenericServicUsers.find(new Users(), Long.parseLong(id));
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			return new ResponseEntity<>("Invalid user id "+id+"!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping() 
	public ResponseEntity<?> saveUser(@RequestBody @Valid Users user,Errors error) {
		if(error.hasErrors()) { 
			return new ResponseEntity<>(error.getFieldError().getDefaultMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try { 
			iGenericServicUsers.create(user);
			return new ResponseEntity<>("User saved successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping() 
	public ResponseEntity<?> updateUser(@RequestBody @Valid Users user,Errors error) {
		if(error.hasErrors()) { 
			return new ResponseEntity<>(error.getFieldError().getDefaultMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try { 
			iGenericServicUsers.update(user);
			return new ResponseEntity<>("User update successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteUser(Users user,@PathVariable String id) { 
		try {
			user = iGenericServicUsers.find(new Users(), Long.parseLong(id));
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(user == null) {
			return new ResponseEntity<>("Invalid user id "+id+" !", HttpStatus.BAD_REQUEST);
		}
		iGenericServicUsers.delete(user);
		return new ResponseEntity<>("User deleted successfully!", HttpStatus.GONE);
	}
 
}
