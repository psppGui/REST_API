package com.apirestspringboot.apirest.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Users")
public class UserModel {
    private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String name;
	@Column(nullable = false, length = 100)
	private String cpf;
	@Column(nullable = false, unique = true, length = 200)
	private String email;
	@Column(nullable = false, length = 100)
	private String modelCar;
	@Column(nullable = false, length = 50)
	private String celNumber;
	@Column(nullable = false, length = 200)
	private String address;
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	public static long getSerialversionuid() {
	 	return serialVersionUID;
    }
}
