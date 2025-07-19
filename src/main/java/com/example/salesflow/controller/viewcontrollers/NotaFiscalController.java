package com.example.salesflow.controller.viewcontrollers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.salesflow.controller.dto.cadastro.NotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.NotaFiscalPesquisaDTO;
import com.example.salesflow.controller.mappers.NotaFiscalMapper;
import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.model.TransacaoType;
import com.example.salesflow.service.NotasService;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notas_fiscais")
@RequiredArgsConstructor
public class NotaFiscalController {
    
    private final NotasService notaFiscalService;
    private final NotaFiscalMapper mapper;

    @GetMapping("/cadastrar_nota")
    public String cadastroNotaFiscal(Model model){
        return "pages/notas_fiscais";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@ModelAttribute("nota") @Valid NotaFiscalCadastroDTO dto,
                                    BindingResult result, Model model){
        if (result.hasErrors()) {
            return "pages/notas_fiscais";
        }
        notaFiscalService.salvar(dto);

        return "redirect:/notas_fiscais/listar";
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
    public ResponseEntity<Page<NotaFiscalPesquisaDTO>> pesquisa(
                @RequestParam(value="tipo_transacao", required=false) 
                TransacaoType tipoTransacao,
                @RequestParam(value = "cliente_cpf", required=false)
                String clienteCpf, 
                @RequestParam(value = "fornecedor_cnpj", required=false)
                String fornecedorCnpj,
                @RequestParam(value = "data_inicio", required=false)
                LocalDate dataInicio,
                @RequestParam(value = "data_final", required=false)
                LocalDate dataFinal,
                @RequestParam(value="pagina", defaultValue= "1")
                Integer pagina,
                @RequestParam(value="tamanho-pagina", defaultValue="1")
                Integer tamanhoPagina
    ){
        Page<NotaFiscal> paginaResultado = notaFiscalService.pesquisa(tipoTransacao, 
                    clienteCpf, fornecedorCnpj, dataInicio, dataFinal, pagina, tamanhoPagina);

        Page<NotaFiscalPesquisaDTO> resultado = paginaResultado.map(mapper::toDTO);
        
        return ResponseEntity.ok(resultado);
    }
    

}
