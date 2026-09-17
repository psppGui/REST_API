package com.apirestspringboot.apirest.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestspringboot.apirest.dto.UserDto;
import com.apirestspringboot.apirest.model.UserModel;
import com.apirestspringboot.apirest.service.UserService;


import jakarta.validation.Valid;
import tools.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class UserController {

	private UserService userService;
	private ObjectMapper objectMapper;
	
	public UserController(UserService userService, ObjectMapper objectMapper) {
		this.userService = userService;
		this.objectMapper = objectMapper;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
		if(userService.existsByModelCarAndCelNumber(userDto.getModelCar(), userDto.getCelNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: user with modelCar and celNumber already exists in database.");
		}
		
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object> getUsers(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(userService.retornaTodos(pageable));
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> returnUserById(@PathVariable UUID id) {
		Optional<UserModel> userOptional = userService.findById(id);
		
		if(userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
		UserModel userModel = new UserModel();
		userModel = userOptional.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(userModel);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> atualizeUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto) {
		Optional<UserModel> userOptional = userService.findById(id);
		
		if(userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in database.");
		}
		
		UserModel userModel = userOptional.get();
		BeanUtils.copyProperties(userDto, userModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<Object> atualizePartialUser(@PathVariable UUID id, @RequestBody Map<String, Object> camposAtualizados) throws Exception {
		Optional<UserModel> userOptional = userService.findById(id);
		
		if(userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in database.");
		}
		
		UserModel userModel = new UserModel();
		userModel = userOptional.get();
		objectMapper.updateValue(userModel, camposAtualizados);
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
		Optional<UserModel> userOptional = userService.findById(id);
		
		if(userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
		UserModel userModel = userOptional.get();
		userService.deletarUser(userModel);
		
		return ResponseEntity.status(HttpStatus.OK).body("User deleted.");
	}
}
