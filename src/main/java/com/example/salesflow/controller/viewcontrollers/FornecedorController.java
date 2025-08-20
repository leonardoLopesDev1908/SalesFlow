package com.example.salesflow.controller.viewcontrollers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salesflow.controller.dto.cadastro.FornecedorCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.FornecedorPesquisaDTO;
import com.example.salesflow.controller.mappers.FornecedorMapper;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.service.FornecedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final FornecedorMapper fornecedorMapper;

    @GetMapping("/cadastro")
    public String paginaFornecedores(Model model){
        return "pages/fornecedor-cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute @Valid FornecedorCadastroDTO dto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("erro", "Preencha corretamente os campos");
            return "pages/fornecedor-cadastro";
        }
        
        try{
            fornecedorService.salvar(fornecedorMapper.toEntity(dto));
            model.addAttribute("mensagem", "Fornecedor cadastrado com sucesso");
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("fornecedor", dto);
        model.addAttribute("currentPage", "fornecedores");
        
        return "pages/fornecedor-cadastro";
    }
    
    @GetMapping("{id}")
    public ResponseEntity<FornecedorPesquisaDTO> obterPorId(@PathVariable("id") String id){
        var idFornecedor = UUID.fromString(id);
        
        return fornecedorService
        .obterPorId(idFornecedor)
        .map(fornecedor -> {
            FornecedorPesquisaDTO dto = fornecedorMapper.toDTO(fornecedor);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public String pesquisar(Model model,
    @RequestParam(value= "nomeFantasia", required=false) String nomeFantasia,
    @RequestParam(value= "razaoSocial", required=false) String razaoSocial,
    @RequestParam(value= "cnpj", required=false) String cnpj,
    @RequestParam(value= "email", required=false) String email,
    @RequestParam(value= "telefone", required=false) String telefone,
    @RequestParam(value= "pagina", defaultValue="0") Integer pagina,
    @RequestParam(value= "tamanhoPagina", defaultValue="10") Integer tamanhoPagina){
        
        Page<Fornecedor> fornecedores = fornecedorService.pesquisa(nomeFantasia, razaoSocial, cnpj, email, 
        telefone, pagina, tamanhoPagina);
        
        List<FornecedorPesquisaDTO> fornecedoresDto = fornecedores.getContent().stream()
        .map(fornecedorMapper::toDTO)
        .toList();
        
        model.addAttribute("titulo", "Fornecedores");
        model.addAttribute("fornecedores", fornecedoresDto);
        model.addAttribute("nomeFantasia", nomeFantasia);
        model.addAttribute("razaoSocial", razaoSocial);
        model.addAttribute("cnpj", cnpj);
        model.addAttribute("email", email);
        model.addAttribute("telefone", telefone);
        model.addAttribute("currentPage", "fornecedores");
        
        return "pages/lista-fornecedores";
    }
}
