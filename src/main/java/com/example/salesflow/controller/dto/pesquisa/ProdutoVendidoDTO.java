package com.example.salesflow.controller.dto.pesquisa;

public record ProdutoVendidoDTO(
    String nomeProduto,
    Long quantidadeVenda 
) {
    public ProdutoVendidoDTO(String nomeProduto, Long quantidadeVenda){
        this.nomeProduto = nomeProduto;
        this.quantidadeVenda = quantidadeVenda;
    }
}
