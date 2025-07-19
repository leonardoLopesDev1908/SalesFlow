package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.repository.ClienteRepository;
import static com.example.salesflow.repository.ClienteSpecs.cpfEqual;
import static com.example.salesflow.repository.ClienteSpecs.emailEqual;
import static com.example.salesflow.repository.ClienteSpecs.nomeLike;
import static com.example.salesflow.repository.ClienteSpecs.telefoneEqual;
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


    public Page<Cliente> pesquisa(
            String nome, String cpf, String email, String telefone,
            Integer pagina, Integer tamanhoPagina){

        Specification<Cliente> specs = null;

        if (nome != null && !nome.isEmpty()){
            specs = (specs == null) ? nomeLike(nome) : specs.and(nomeLike(nome));
        }
        if(cpf != null && !cpf.isEmpty()){
            specs = (specs == null) ? cpfEqual(cpf) : specs.and(cpfEqual(cpf));
        } 
        if (email != null && !email.isEmpty()) {
            specs = (specs == null) ? emailEqual(email) : specs.and(emailEqual(email));
        }
        if (telefone != null && !telefone.isEmpty()){
            specs = (specs == null) ? telefoneEqual(telefone) : specs.and(telefoneEqual(telefone));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return clienteRepository.findAll(specs, pageRequest);
    }
}
