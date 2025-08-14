package com.example.salesflow.controller.viewcontrollers;

import java.time.LocalDate;
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

import com.example.salesflow.controller.dto.cadastro.PedidoCadastroDTO;
import com.example.salesflow.controller.dto.pesquisa.PedidoPesquisaDTO;
import com.example.salesflow.controller.mappers.PedidoMapper;
import com.example.salesflow.model.Pedido;
import com.example.salesflow.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {
    
    private final PedidoService service;
    private final PedidoMapper mapper;

    @GetMapping("/solicitar")
    public String solicitacaoPedido(Model model){
        return "pages/solicitar-pedido";
    }

    @PostMapping("/solicitar_pedido")
    public String solicitar(@ModelAttribute @Valid PedidoCadastroDTO dto,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("erro", "Erro na solicitação do pedido");
            return "pages/solicitar-pedido";
        }                   
        try{
            service.salvar(dto);
            model.addAttribute("mensagem", "Solicitação feita!");
            return "pages/solicitar-pedido";
        }catch (IllegalArgumentException e){
            System.err.print(e.getMessage());
            model.addAttribute("erro", e.getMessage());
            return "pages/solicitar-pedido";
        }     
    }

    @GetMapping
    public String pesquisa(Model model,
                    @RequestParam(value="numPedido", required = false) Long numPedido,
                    @RequestParam(value="palavra", required = false) String palavraChave,
                    @RequestParam(value="usuario", required = false) String loginUsuario,
                    @RequestParam(value="departamento", required = false) String departamento,
                    @RequestParam(value="dataInicio", required = false) LocalDate dataInicio,
                    @RequestParam(value="dataFinal", required = false) LocalDate dataFinal,
                    @RequestParam(value="pagina", defaultValue = "0") Integer pagina,
                    @RequestParam(value="tamanhoPagina", defaultValue= "10") Integer tamanhoPagina){
        
        Page<Pedido> paginaResultado = service.pesquisa(numPedido, palavraChave, loginUsuario, departamento,
                         dataInicio, dataFinal, pagina, tamanhoPagina);
        
        model.addAttribute("titulo", "Notas");
        model.addAttribute("numPedido", numPedido);
        model.addAttribute("palavra", palavraChave);
        model.addAttribute("departamento", departamento);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFinal", dataFinal);
        List<PedidoPesquisaDTO> resultado = paginaResultado.getContent()
                        .stream()
                        .map(mapper::toDTO)
                        .toList();
        model.addAttribute("pedidos", resultado);

        return "pages/lista-pedidos";
    }
                    

}
