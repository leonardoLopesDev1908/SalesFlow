package com.example.salesflow.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesflow.controller.dto.cadastro.ClienteCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.ClientePesquisaDTO;
import com.example.salesflow.controller.mappers.ClienteMapper;
import com.example.salesflow.model.Cliente;
import com.example.salesflow.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrar(@RequestBody @Valid ClienteCadastroDTO dto){
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteService.salvar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePesquisaDTO> buscarCliente(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);

        return clienteService
                .obterPorId(idCliente)
                .map(cliente -> {
                    ClientePesquisaDTO dto = clienteMapper.toDTO(cliente);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
