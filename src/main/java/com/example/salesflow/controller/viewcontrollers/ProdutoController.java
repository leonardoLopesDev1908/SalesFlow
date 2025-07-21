package com.example.salesflow.controller.viewcontrollers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.salesflow.controller.dto.cadastro.ProdutoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.FornecedorPesquisaDTO;
import com.example.salesflow.controller.dto.pesquisa.ProdutoPesquisaDTO;
import com.example.salesflow.controller.mappers.ProdutoMapper;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.model.Produto;
import com.example.salesflow.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    
    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @GetMapping("/cadastro")
    public String paginaProdutos(Model model){
        return "pages/produto-cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute @Valid ProdutoCadastroDTO dto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("erro", "Preencha corretamente os campos");
            return "pages/produto-cadastro";
        }
        try {
            produtoService.salvar(produtoMapper.toEntity(dto));
            return "pages/produto-cadastro";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("produto", dto);
        return "pages/produto-cadastro";
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoPesquisaDTO> obterPorId(@PathVariable("id") String id){
        var idProduto = Long.valueOf(id);

        return produtoService   
            .obterPorId(idProduto)
            .map(produto -> {
                ProdutoPesquisaDTO dto = produtoMapper.toDTO(produto);
                return ResponseEntity.ok(dto);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public String pesquisar(Model model,
                            @RequestParam(value= "nome", required=false) String nome,
                            @RequestParam(value= "descricao", required=false) String descricao,
                            @RequestParam(value= "marca", required=false) String marca,
                            @RequestParam(value= "preco", required=false) BigDecimal preco,
                            @RequestParam(value= "estoque", required=false) Integer estoque,
                            @RequestParam(value= "pagina", defaultValue="0") Integer pagina,
                            @RequestParam(value= "tamanhoPagina", defaultValue="10") Integer tamanhoPagina){
        
        Page<Fornecedor> fornecedores = produtoService.pesquisa(nome, descricao, marca, preco, 
                                                estoque, pagina, tamanhoPagina);
        
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
            
        return "pages/lista-fornecedores";
    }

}
