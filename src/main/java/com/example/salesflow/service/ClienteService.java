package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.repository.ClienteRepository;
import com.example.salesflow.validator.ClienteValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final ClienteValidator validator;

    public Cliente salvar(Cliente cliente){
        validator.validar(cliente);
        return clienteRepository.save(cliente);
    }

    public void deletar(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public Optional<Cliente> obterPorId(UUID id){
        return clienteRepository.findById(id);
    }

    public Cliente obterPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> obterPorNome(String nome){
        return clienteRepository.findByNomeLike(nome);
    } 

}
