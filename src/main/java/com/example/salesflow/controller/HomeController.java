package com.example.salesflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.salesflow.service.ClienteService;
import com.example.salesflow.service.FornecedorService;
import com.example.salesflow.service.NotasService;
import com.example.salesflow.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    
    private final ProdutoService produtoService;

    private final FornecedorService fornecedorService;

    private final ClienteService clienteService;

    private final NotasService notasService;

    @GetMapping
    public String home(Model model){
        return "home.html";
    }

}
