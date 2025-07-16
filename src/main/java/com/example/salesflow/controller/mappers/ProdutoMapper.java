package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.ProdutoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.ProdutoPesquisaDTO;
import com.example.salesflow.model.Produto;


@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itensNotaFiscal", ignore = true)
    Produto toEntity(ProdutoCadastroDTO dto);

    ProdutoPesquisaDTO toDTO(Produto produto);
}
