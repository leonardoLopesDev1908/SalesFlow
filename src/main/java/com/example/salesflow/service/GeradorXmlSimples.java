package com.example.salesflow.service;

import org.springframework.stereotype.Component;

import com.example.salesflow.controller.dto.pesquisa.NotaFiscalPesquisaDTO;
import com.example.salesflow.controller.dto.summary.ClienteSummaryDTO;
import com.example.salesflow.controller.dto.summary.FornecedorSummaryDTO;
import com.example.salesflow.controller.dto.summary.ProdutoSummaryDTO;
import com.example.salesflow.model.Empresa;
import com.example.salesflow.model.NotaFiscal;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeradorXmlSimples {

    private Empresa empresa;

    public static <T> String gerarXML(T nf) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("notaFiscal", NotaFiscal.class);
        xstream.alias("cliente", ClienteSummaryDTO.class);
        xstream.alias("fornecedor", FornecedorSummaryDTO.class);
        xstream.alias("produto", ProdutoSummaryDTO.class);

        xstream.addImplicitCollection(NotaFiscalPesquisaDTO.class, "itens");

        String xml = xstream.toXML(nf);

        return xml;
    }
}