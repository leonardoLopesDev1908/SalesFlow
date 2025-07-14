package com.example.salesflow.controller;

import org.springframework.stereotype.Controller;

import com.example.salesflow.service.ClienteService;
import com.example.salesflow.service.FornecedorService;
import com.example.salesflow.service.NotasService;
import com.example.salesflow.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final ProdutoService produtoService;

    private final FornecedorService fornecedorService;

    private final ClienteService clienteService;

    private final NotasService notasService;

    

}
