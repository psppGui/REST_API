package com.apirestspringboot.apirest.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto{
    
	@NotBlank
	private String name;
	@NotBlank
	private String cpf;
	@NotBlank
	private String email;
	@NotBlank
	private String modelCar;
	@NotBlank
	private String celNumber;
	@NotBlank
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public String getCelNumber() {
		return celNumber;
	}
	public void setCelNumber(String celNumber) {
		this.celNumber = celNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}