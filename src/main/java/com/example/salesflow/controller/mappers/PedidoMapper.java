package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.salesflow.controller.dto.cadastro.PedidoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.PedidoPesquisaDTO;
import com.example.salesflow.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    
    @Mapping(target = "numPedido", ignore=true)
    @Mapping(target = "data", ignore=true)
    @Mapping(target = "usuario", ignore=true)
    @Mapping(target = "status", ignore=true)
    Pedido toEntity(PedidoCadastroDTO dto);

    PedidoPesquisaDTO toDTO(Pedido pedido);
}
