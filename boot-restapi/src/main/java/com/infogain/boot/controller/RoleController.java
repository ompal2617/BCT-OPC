package com.infogain.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.boot.entity.Role;
import com.infogain.boot.services.IGenericService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private IGenericService<Role> iGenericServicRole;

	@GetMapping()
	public ResponseEntity<?> getAllRole() {
		List<Role> listRole = iGenericServicRole.fetchAll(new Role()," order by id DESC");
		if (listRole.isEmpty()) {
			return new ResponseEntity<>("NO role found!", HttpStatus.NO_CONTENT);
		}

		listRole.parallelStream().forEach(role -> role.getUsers().forEach(user -> {
			System.out.println(user.getUsername());
		}));

		return new ResponseEntity<>(listRole, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(Role role, @PathVariable String id) {
		try {
			role = iGenericServicRole.find(new Role(), Long.parseLong(id),"");
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (role == null) {
			return new ResponseEntity<>("Invalid Role id " + id + "!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> saveRole(@RequestBody @Valid Role role, Errors error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(error.getFieldError().getDefaultMessage().toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			iGenericServicRole.create(role);
			return new ResponseEntity<>("Role saved successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping()
	public ResponseEntity<?> updateRole(@RequestBody @Valid Role role, Errors error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(error.getFieldError().getDefaultMessage().toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			iGenericServicRole.update(role);
			return new ResponseEntity<>("Role updated successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(Role role ,@PathVariable String id) {
		try {
			role = iGenericServicRole.find(new Role(), Long.parseLong(id),"");
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (role == null) {
			return new ResponseEntity<>("Invalid Role id " + id + "!", HttpStatus.BAD_REQUEST);
		}
		iGenericServicRole.delete(role);
		return new ResponseEntity<>("Role delete successfully!", HttpStatus.GONE);
	}

}
