package com.example.salesflow.controller.viewcontrollers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping
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

    @GetMapping("/pesquisar")
    public String pesquisa(Model model,
                    @RequestParam(value="numPedido", required = false) Long numPedido,
                    @RequestParam(value="palavraChave", required = false) String palavraChave,
                    @RequestParam(value="loginUsuario", required = false) String loginUsuario,
                    @RequestParam(value="departamento", required = false) String departamento,
                    @RequestParam(value="dataInicio", required = false) LocalDate dataInicio,
                    @RequestParam(value="dataFinal", required = false) LocalDate dataFinal,
                    @RequestParam(value="pagina", defaultValue = "0") Integer pagina,
                    @RequestParam(value="tamanhoPagina", defaultValue= "10") Integer tamanhoPagina){
    
        Page<Pedido> paginaResultado = service.pesquisa(numPedido, palavraChave, loginUsuario, departamento,
                         dataInicio, dataFinal, pagina, tamanhoPagina);
        
        model.addAttribute("titulo", "Pedidos");
        model.addAttribute("numPedido", numPedido);
        model.addAttribute("palavraChave", palavraChave);
        model.addAttribute("loginUsuario", loginUsuario);
        model.addAttribute("departamento", departamento);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFinal", dataFinal);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");

        List<PedidoPesquisaDTO> resultado = paginaResultado.getContent()
                        .stream()
                        .map(pedido -> {
                            PedidoPesquisaDTO dto = mapper.toDTO(pedido);
                            dto.setDepartamento(pedido.getUsuario().getDepartamento());
                            dto.setNomeUsuario(pedido.getUsuario().getLogin());
                            dto.setDataFormatada(pedido.getData().format(formatador));
                            return dto;
                        })
                        .toList();

        model.addAttribute("pedidos", resultado);

        return "pages/lista-pedidos";
    }

    @GetMapping("{id}")
    @ResponseBody
    public PedidoPesquisaDTO getPedido(@PathVariable Long id){
        Pedido pedido = service.buscarPorId(id);
        PedidoPesquisaDTO dto = mapper.toDTO(pedido);
        
        dto.setNomeUsuario(pedido.getUsuario().getLogin());
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
        dto.setDataFormatada(pedido.getData().format(formatador));
        dto.setDepartamento(pedido.getUsuario().getDepartamento());

        return dto;
    }

    @PutMapping("{id}/status")
    @ResponseBody
    public ResponseEntity<Void> atualizarPedido(@PathVariable Long id, @RequestParam String novoStatus){
        service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok().build();
    }
                    

}
