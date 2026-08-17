package com.apirestspringboot.apirest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirestspringboot.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByName(String name);

    Boolean existsByModelCarAndCelNumber(String modelCar, String celNumber);

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    Optional<UserModel> buscarPorEmailNativo(@Param("email") String email);
}