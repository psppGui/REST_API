package com.apirestspringboot.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apirestspringboot.apirest.repository.UserRepository;
import com.apirestspringboot.model.UserModel;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Page<UserModel> retornaTodos(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<UserModel> retornaTodos() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> findByNome(String nome) {
        return userRepository.findByName(nome);
    }

    @Transactional
    public void deletarUser(UserModel userModel) {
        userRepository.delete(userModel);
    }

    public boolean existsByModelCarAndCelNumber(String modelCar, String celNumber) {
        return userRepository.existsByModelCarAndCelNumber(modelCar, celNumber);
    }

    public Optional<UserModel> retornaUserPorEmail(String email) {
        return userRepository.buscarPorEmailNativo(email);
    }
}
