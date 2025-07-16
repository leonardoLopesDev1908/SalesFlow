package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.FornecedorCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.FornecedorPesquisaDTO;
import com.example.salesflow.model.Fornecedor;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    
    @Mapping(target = "id", ignore=true)
    @Mapping(target = "notasFiscais", ignore=true)
    Fornecedor toEntity(FornecedorCadastroDTO dto);
    
    @Mapping(target = "notasFiscais", ignore=true)
    FornecedorPesquisaDTO toDTO(Fornecedor fornecedor);

}
