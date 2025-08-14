package com.example.salesflow.controller.mappers;

import org.mapstruct.Mapper;

import com.example.salesflow.controller.dto.cadastro.PedidoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.PedidoPesquisaDTO;
import com.example.salesflow.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    
    Pedido toEntity(PedidoCadastroDTO dto);

    PedidoPesquisaDTO toDTO(Pedido pedido);
}
