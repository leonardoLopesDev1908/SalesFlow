package com.example.salesflow.controller.viewcontrollers;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salesflow.controller.dto.cadastro.ClienteCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.ClientePesquisaDTO;
import com.example.salesflow.controller.mappers.ClienteMapper;
import com.example.salesflow.exceptions.RegistroDuplicadoException;
import com.example.salesflow.model.Cliente;
import com.example.salesflow.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping("/cadastro")
    public String paginaClientes(Model model){
        return "pages/cliente-cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute @Valid ClienteCadastroDTO dto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("erro", "Preencha corretamente os campos");
            return "pages/cliente-cadastro";
        }
        
        try{
            clienteService.salvar(clienteMapper.toEntity(dto));
            model.addAttribute("mensagem", "Cliente cadastrado com sucesso");
        } catch(RegistroDuplicadoException e){
            System.out.println(e.getMessage());
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("cliente", dto);
        
        return "pages/cliente-cadastro";
    }

    @GetMapping
    public String pesquisar(Model model,
                            @RequestParam(value= "nome", required=false) String nome,
                            @RequestParam(value= "cpf", required=false) String cpf,
                            @RequestParam(value= "email", required=false) String email,
                            @RequestParam(value= "telefone", required=false) String telefone,
                            @RequestParam(value= "pagina", defaultValue="0")Integer pagina,
                            @RequestParam(value= "tamanho-pagina", defaultValue="10")Integer tamanhoPagina
                            ){

        Page<Cliente> clientes = clienteService.pesquisa(nome, cpf, email, telefone, pagina, tamanhoPagina);
                                
        List<ClientePesquisaDTO> clientesDto = clientes.getContent().stream()
                .map(clienteMapper::toDTO)
                .toList();

        model.addAttribute("titulo", "Clientes");
        model.addAttribute("clientes", clientesDto);
        model.addAttribute("nome", nome);
        model.addAttribute("cpf", cpf);
        model.addAttribute("email", email);
        model.addAttribute("telefone", telefone);

        return "pages/lista-clientes";    
    }

}
