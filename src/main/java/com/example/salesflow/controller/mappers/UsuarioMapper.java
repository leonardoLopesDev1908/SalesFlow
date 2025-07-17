package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.UsuarioDTO;
import com.example.salesflow.model.Usuario;

@Mapper(componentModel= "spring")
public interface UsuarioMapper {
    
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioDTO dto);

}
