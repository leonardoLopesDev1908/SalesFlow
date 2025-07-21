package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.salesflow.model.Produto;
import com.example.salesflow.repository.ProdutoRepository;
import static com.example.salesflow.repository.ProdutoSpecs.descricaoLike;
import static com.example.salesflow.repository.ProdutoSpecs.marcaEqual;
import static com.example.salesflow.repository.ProdutoSpecs.nomeLike;

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

    public Page<Produto> pesquisa(String nome, String descricao, String marca,
                                        Integer pagina, Integer tamanhoPagina){
        
        Specification<Produto> specs = null;  
        
        if(nome != null && !nome.isEmpty()){
            specs = (specs == null) ? nomeLike(nome) : specs.and(nomeLike(nome));
        }
        if(descricao != null && !descricao.isEmpty()){
            specs = (specs == null) ? descricaoLike(descricao) : specs.and(descricaoLike(descricao));
        }
        if(marca != null && !marca.isEmpty()){
            specs = (specs == null) ? marcaEqual(marca) : specs.and(marcaEqual(marca));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return produtoRepository.findAll(specs, pageRequest);
    }
}
