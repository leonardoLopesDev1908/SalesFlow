package com.example.salesflow.service;

import org.springframework.stereotype.Component;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.Empresa;
import com.example.salesflow.model.ItemNotaFiscal;
import com.example.salesflow.model.NotaFiscal;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeradorXmlSimples {

    private Empresa empresa;

    public static String gerarXML(NotaFiscal nf) {

        XStream xstream = new XStream(new DomDriver());

        xstream.alias("notaFiscal", NotaFiscal.class);
        xstream.alias("emitente", Empresa.class);
        xstream.alias("destinatario", Cliente.class);
        xstream.alias("item", ItemNotaFiscal.class);
        xstream.addImplicitCollection(NotaFiscal.class, "itens");

        String xml = xstream.toXML(nf);

        return xml;
    }
}