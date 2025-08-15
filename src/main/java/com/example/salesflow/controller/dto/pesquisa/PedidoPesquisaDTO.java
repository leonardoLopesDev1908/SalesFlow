package com.example.salesflow.controller.dto.pesquisa;

import lombok.Data;

@Data
public class PedidoPesquisaDTO {
    
    private Long numPedido;
    private String titulo;
    private String descricao;
    private String nomeUsuario;
    private String departamento;
    private String dataFormatada;
    
    public void setDepartamento(String departamento){
        this.departamento = departamento;
    }
}
