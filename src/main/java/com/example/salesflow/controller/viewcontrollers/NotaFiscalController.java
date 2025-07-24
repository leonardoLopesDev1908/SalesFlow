package com.example.salesflow.controller.viewcontrollers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salesflow.controller.dto.cadastro.NotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.NotaFiscalPesquisaDTO;
import com.example.salesflow.controller.mappers.NotaFiscalMapper;
import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.model.TransacaoType;
import com.example.salesflow.service.NotasService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notas_fiscais")
@RequiredArgsConstructor
public class NotaFiscalController {
    
    private final NotasService notaFiscalService;
    private final NotaFiscalMapper mapper;

    @GetMapping("/cadastro")
    public String cadastroNotaFiscal(Model model){
        return "pages/nota-cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute @Valid NotaFiscalCadastroDTO dto,
                                    BindingResult result, Model model){
        
        if (result.hasErrors()) {
            System.out.println(result);
            model.addAttribute("erro", "Preencha corretamente os campos");
            return "pages/nota-cadastro";
        }
        try {
            notaFiscalService.salvar(dto);
            System.out.println("Saindo do service");
            model.addAttribute("mensagem", "Nota registrada com sucesso");
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            System.out.println(e.getMessage());
            return "pages/nota-cadastro";
        }

        System.out.println("Retornando pagina de cadastro");
        model.addAttribute("nota", dto); 
        return "pages/nota-cadastro";
    }

    @GetMapping("{num_nota}")
    public NotaFiscal obterPorId(@PathVariable("num_nota") Long id){
        return notaFiscalService.buscaPorId(id);
    }

    @GetMapping("/por_cpf")
    public List<NotaFiscalPesquisaDTO> buscaNotaPorCpf(@RequestParam String cpf) {
        List<NotaFiscal> notas = notaFiscalService.buscaPorCpf(cpf);

        return notas
            .stream()
            .map(nota -> mapper.toDTO(nota))
            .collect(Collectors.toList());
    } 

    @GetMapping("/por_cnpj")
    public List<NotaFiscalPesquisaDTO> buscaNotaPorCnpj(@RequestParam String cnpj){
        List<NotaFiscal> notas = notaFiscalService.buscaPorCnpj(cnpj);

        return notas
            .stream()
            .map(nota -> mapper.toDTO(nota))
            .collect(Collectors.toList());
    }

    @GetMapping
    public String pesquisa(Model model,
                @RequestParam(value = "tipoTransacao", required = false) TransacaoType tipoTransacao,
                @RequestParam(value = "clienteCpf", required = false) String clienteCpf,
                @RequestParam(value = "fornecedorCnpj", required = false) String fornecedorCnpj,
                @RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
                @RequestParam(value = "dataFinal", required = false) LocalDate dataFinal,
                @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina){
        
        Page<NotaFiscal> paginaResultado = notaFiscalService.pesquisa(tipoTransacao, 
                    clienteCpf, fornecedorCnpj, dataInicio, dataFinal, pagina, tamanhoPagina);

        List<NotaFiscalPesquisaDTO> resultado = paginaResultado.getContent()
                    .stream()
                    .map(mapper::toDTO)
                    .toList();

        model.addAttribute("titulo", "Notas");
        model.addAttribute("notas", resultado);
        model.addAttribute("tipoTransacao", tipoTransacao);
        model.addAttribute("clienteCpf", clienteCpf);
        model.addAttribute("fornecedorCnpj", fornecedorCnpj);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFinal", dataFinal);

        return "pages/lista-notas_fiscais";
    }
}
