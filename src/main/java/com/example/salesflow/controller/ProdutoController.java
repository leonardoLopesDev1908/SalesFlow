package com.example.salesflow.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesflow.controller.dto.cadastro.ProdutoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.ProdutoPesquisaDTO;
import com.example.salesflow.controller.mappers.ProdutoMapper;
import com.example.salesflow.model.Produto;
import com.example.salesflow.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    
    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@RequestBody @Valid ProdutoCadastroDTO dto){
        Produto produto = produtoMapper.toEntity(dto);
        return produtoService.salvar(produto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoPesquisaDTO> obterPorId(@PathVariable("id") String id){
        var idProduto = Long.valueOf(id);

        return produtoService   
            .obterPorId(idProduto)
            .map(produto -> {
                ProdutoPesquisaDTO dto = produtoMapper.toDTO(produto);
                return ResponseEntity.ok(dto);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoPesquisaDTO>> buscaPorMarca(@RequestParam String marca){
        List<Produto> produtos = produtoService.obterPorMarca(marca);
        
        List<ProdutoPesquisaDTO> dtos = produtos 
            .stream()
            .map(produto -> produtoMapper.toDTO(produto))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }
}
