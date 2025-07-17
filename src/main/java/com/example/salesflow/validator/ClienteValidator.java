package com.example.salesflow.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.salesflow.exceptions.RegistroDuplicadoException;
import com.example.salesflow.model.Cliente;
import com.example.salesflow.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteValidator {
    
    private final ClienteRepository repository;

    public void validar(Cliente cliente){
        if(existeCliente(cliente)){
            throw new RegistroDuplicadoException("Esse cliente já está cadastrado");
        }
    }

    private boolean existeCliente(Cliente cliente){
        Optional<Cliente> clienteEncontrado = repository.findByNomeAndCpf(
                        cliente.getNome(), cliente.getCpf());
    
        if(cliente.getId() == null){
            return clienteEncontrado.isPresent();
        }

        return !cliente.getId().equals(clienteEncontrado.get().getId()) && clienteEncontrado.isPresent();
    }

}
