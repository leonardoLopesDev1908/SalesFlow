package com.example.salesflow.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salesflow.service.NotasService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final NotasService service;

    @GetMapping("/")
    public String home(@RequestParam(name="periodo", defaultValue="mes_atual") String periodo, Model model) throws IOException {
        List<BigDecimal> dados = service.totaisDoMes();     
        
        LocalDateTime dataFinal = LocalDateTime.now();
        LocalDateTime dataInicial = switch(periodo){
            case "ultimos_30_dias" -> dataFinal.minusDays(30);
            case "ultimos_6_meses" -> dataFinal.minusMonths(6);
            case "ultimo_ano" -> dataFinal.minusYears(1);
            default -> LocalDateTime.of(dataFinal.getYear(), dataFinal.getMonth(), 1, 0, 0, 0, 0);
        };
        
        // try {
        //     List<ProdutoVendidoDTO> ranking = service.topProdutos(dataInicial, dataFinal);
        //     DefaultCategoryDataset datasetRanking = new DefaultCategoryDataset();
        //     for (ProdutoVendidoDTO produto : ranking) {
        //         System.out.println(produto);
        //         datasetRanking.addValue(produto.quantidadeVenda(), "Quantidade", produto.nomeProduto());
        //     }
        //     JFreeChart chartRanking = ChartFactory.createBarChart(
        //         "Top 10 Produtos Vendidos", "Produto", "Quantidade", datasetRanking
        //     );

        //     File pastaImagens = new File("src/main/resources/static/images");
        //     if (!pastaImagens.exists()) {
        //         pastaImagens.mkdirs();
        //     }
        //     String pathRanking = "/images/graficoTop10Vendas.png";
        //     File arquivo = new File("salesflow/src/main/resources/static" + pathRanking);
            
        //     ChartUtils.saveChartAsPNG(arquivo, chartRanking, 400, 300);
        //     model.addAttribute("graficoVendas", pathRanking);
        // }catch(IllegalArgumentException e){
        //     System.err.print(e.getMessage());
        // }
        

        model.addAttribute("vendas", dados.get(0));
        model.addAttribute("compras", dados.get(1));

        return "pages/home";
    }

    // @GetMapping("/")
    // public String home(@RequestParam(name="periodo", defaultValue="mes_atual") String periodo, Model model) throws IOException {
    //     return "pages/home";
    // }


}
