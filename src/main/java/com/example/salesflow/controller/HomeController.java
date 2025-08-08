package com.example.salesflow.controller;



import java.io.IOException;

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

    // @GetMapping("/")
    // public String home(@RequestParam(name="periodo", defaultValue="mes_atual") String periodo, Model model) throws IOException {
    //     List<BigDecimal> dados = service.totaisDoMes();     

    //     LocalDate dataFinal = LocalDate.now();
    //     LocalDate dataInicial = switch(periodo){
    //         case "ultimos_30_dias" -> dataFinal.minusDays(30);
    //         case "ultimos_6_meses" -> dataFinal.minusMonths(6);
    //         case "ultimo_ano" -> dataFinal.minusYears(1);
    //         default -> LocalDate.of(dataFinal.getYear(), dataFinal.getMonth(), 1);
    //     };
        
    //     try {
    //         List<ProdutoVendidoDTO> ranking = service.topProdutos(dataInicial, dataFinal);
    //         DefaultCategoryDataset datasetRanking = new DefaultCategoryDataset();
    //         for (ProdutoVendidoDTO produto : ranking) {
    //             datasetRanking.addValue(produto.quantidadeVenda(), "Quantidade", produto.nomeProduto());
    //         }
    //         JFreeChart chartRanking = ChartFactory.createBarChart(
    //             "Top 10 Produtos Vendidos", "Produto", "Quantidade", datasetRanking
    //         );
    //         String pathRanking = "/images/graficoTop10Vendas.png";
    //         ChartUtils.saveChartAsPNG(new File("src/main/resources/static" + pathRanking), chartRanking, 400, 300);
    //         model.addAttribute("graficoVendas", pathRanking);
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
        

    //     model.addAttribute("vendas", dados.get(0));
    //     model.addAttribute("compras", dados.get(1));

    //     return "pages/home";
    // }

    @GetMapping("/")
    public String home(@RequestParam(name="periodo", defaultValue="mes_atual") String periodo, Model model) throws IOException {
        return "pages/home";
    }


}
