package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.ItemNotaFiscalCadastroDTO;
import com.example.salesflow.model.ItemNotaFiscal;

@Mapper(componentModel = "spring")
public interface ItemNotasFiscalMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "notaFiscal", ignore = true)
    @Mapping(target = "produto", ignore = true)
    ItemNotaFiscal toEntity(ItemNotaFiscalCadastroDTO dto);

}
