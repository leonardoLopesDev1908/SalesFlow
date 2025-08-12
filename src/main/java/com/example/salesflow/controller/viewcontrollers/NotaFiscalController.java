package com.example.salesflow.controller.viewcontrollers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.salesflow.controller.dto.cadastro.NotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.NotaFiscalPesquisaDTO;
import com.example.salesflow.controller.mappers.NotaFiscalMapper;
import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.service.GeradorXmlSimples;
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
                                    BindingResult result, Model model,
                                    RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("erro", result.getAllErrors());
            return "pages/nota-cadastro";
        }
        try {
            notaFiscalService.salvar(dto);
            model.addAttribute("mensagem", "Nota cadastrada com sucesso");
            return "pages/nota-cadastro";
        } catch (Exception e) {
            System.err.print(e.getMessage());
            model.addAttribute("erro", "Erro no cadastro da nota");
            return "pages/nota-cadastro";
        }
    }

    @GetMapping
    public String pesquisa(Model model,
                @RequestParam(value = "numNota", required = false) Long numNota,
                @RequestParam(value = "tipoTransacao", required = false) String tipoTransacao,
                @RequestParam(value = "clienteCpf", required = false) String clienteCpf,
                @RequestParam(value = "fornecedorCnpj", required = false) String fornecedorCnpj,
                @RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
                @RequestParam(value = "dataFinal", required = false) LocalDate dataFinal,
                @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina){
        
    
        Page<NotaFiscal> paginaResultado = notaFiscalService.pesquisa(numNota, tipoTransacao, 
                    clienteCpf, fornecedorCnpj, dataInicio, dataFinal, pagina, tamanhoPagina);
                    
                    model.addAttribute("titulo", "Notas");
                    model.addAttribute("numNota", numNota);
                    model.addAttribute("tipoTransacao", tipoTransacao);
                    model.addAttribute("clienteCpf", clienteCpf);
                    model.addAttribute("fornecedorCnpj", fornecedorCnpj);
                    model.addAttribute("dataInicio", dataInicio);
                    model.addAttribute("dataFinal", dataFinal);
        List<NotaFiscalPesquisaDTO> resultado = paginaResultado.getContent()
                    .stream()
                    .map(mapper::toDTO)
                    .toList();
        model.addAttribute("notas", resultado);

        return "pages/lista-notas_fiscais";
    }

    @GetMapping("/{id}/visualizar")
    public ResponseEntity<String> visualizarNota(@PathVariable Long id) {
        NotaFiscal nf = notaFiscalService.buscaPorId(id);
        
        if(nf == null){
            System.out.println("Nota não encontrada");
        }
    
        String xml = GeradorXmlSimples.gerarXML(nf);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return new ResponseEntity<>(xml, headers, HttpStatus.OK);
    }
}
