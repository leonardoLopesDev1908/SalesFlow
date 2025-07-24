package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.NotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.NotaFiscalPesquisaDTO;
import com.example.salesflow.model.NotaFiscal;

@Mapper(componentModel = "spring")
public interface NotaFiscalMapper {
    
    @Mapping(target = "numNota", ignore=true)
    @Mapping(target = "cliente", ignore=true)
    @Mapping(target = "data", ignore=true)
    @Mapping(target = "fornecedor", ignore=true)
    @Mapping(target = "itens", ignore=true)
    @Mapping(target = "valorTotal", ignore=true)
    @Mapping(target = "usuario", ignore=true)
    NotaFiscal toEntity(NotaFiscalCadastroDTO dto);
        
    NotaFiscalPesquisaDTO toDTO(NotaFiscal notaFiscal);

}