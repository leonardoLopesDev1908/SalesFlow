package com.example.salesflow.service;

import org.springframework.stereotype.Component;

import com.example.salesflow.controller.dto.visualizacao.ItemNotaFiscalVisualizacaoDTO;
import com.example.salesflow.controller.dto.visualizacao.NotaFiscalVisualizacaoDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeradorXmlSimples {

    public static <T> String gerarXML(T dto) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("notaFiscal", NotaFiscalVisualizacaoDTO.class);
        xstream.alias("item", ItemNotaFiscalVisualizacaoDTO.class);
        
        xstream.addImplicitCollection(NotaFiscalVisualizacaoDTO.class, "itens");

        return xstream.toXML(dto);
    }
}