package com.example.salesflow.controller.restcontrollers;

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

import com.example.salesflow.controller.dto.cadastro.FornecedorCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.FornecedorPesquisaDTO;
import com.example.salesflow.controller.mappers.FornecedorMapper;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.service.FornecedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    private final FornecedorMapper fornecedorMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor cadastrar(@RequestBody @Valid FornecedorCadastroDTO dto){
        Fornecedor fornecedor = fornecedorMapper.toEntity(dto);
        return fornecedorService.salvar(fornecedor);
    }

    @GetMapping("{id}")
    public ResponseEntity<FornecedorPesquisaDTO> obterPorId(@PathVariable("id") String id){
        var idFornecedor = UUID.fromString(id);

        return fornecedorService
                .obterPorId(idFornecedor)
                .map(fornecedor -> {
                    FornecedorPesquisaDTO dto = fornecedorMapper.toDTO(fornecedor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
