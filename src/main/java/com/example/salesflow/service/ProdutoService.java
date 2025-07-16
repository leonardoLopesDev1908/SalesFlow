package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.salesflow.model.Produto;
import com.example.salesflow.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Optional<Produto> obterPorId(Long id){
        return produtoRepository.findById(id);
    }

    public List<Produto> obterPorMarca(String marca){
        return produtoRepository.findByMarcaLike(marca);
    }

}
