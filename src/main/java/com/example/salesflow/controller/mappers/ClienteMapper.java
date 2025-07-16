package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.ClienteCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.ClientePesquisaDTO;
import com.example.salesflow.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "notasFiscais", ignore = true)
    Cliente toEntity(ClienteCadastroDTO dto);
    
    @Mapping(target = "notasFiscais", ignore = true)
    ClientePesquisaDTO toDTO(Cliente cliente);
}
