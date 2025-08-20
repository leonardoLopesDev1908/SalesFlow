package com.example.salesflow.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salesflow.controller.dto.pesquisa.ProdutoVendidoDTO;
import com.example.salesflow.service.NotasService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final NotasService service;

    @GetMapping("/")
    public String home(@RequestParam(name = "periodo", defaultValue = "mes_atual") String periodo,
                    Model model) {

        List<BigDecimal> dados = service.totaisDoMes();

        LocalDateTime dataFinal = LocalDateTime.now();
        LocalDateTime dataInicial = switch (periodo) {
            case "ultimos_30_dias" -> dataFinal.minusDays(30);
            case "ultimos_6_meses" -> dataFinal.minusMonths(6);
            case "ultimo_ano" -> dataFinal.minusYears(1);
            default -> LocalDateTime.of(dataFinal.getYear(), dataFinal.getMonth(), 1, 0, 0, 0, 0);
        };

        List<ProdutoVendidoDTO> ranking = service.topProdutos(dataInicial, dataFinal);
        List<String> labels = ranking.stream()
                                    .map(ProdutoVendidoDTO::nomeProduto)
                                    .toList();
        List<Long> valores = ranking.stream()
                                    .map(ProdutoVendidoDTO::quantidadeVenda)
                                    .toList();

        model.addAttribute("vendas", dados.get(0));
        model.addAttribute("compras", dados.get(1));
        model.addAttribute("labels", labels);
        model.addAttribute("valores", valores);
        model.addAttribute("currentPage", "home");

        return "pages/home";
    }
}
