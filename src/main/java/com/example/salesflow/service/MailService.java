package com.example.salesflow.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.salesflow.controller.dto.visualizacao.ItemNotaFiscalVisualizacaoDTO;
import com.example.salesflow.controller.dto.visualizacao.NotaFiscalVisualizacaoDTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
    
    private final JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}")
    private String email;

    public String enviarEmail(String destinatario, String assunto, String mensagem, NotaFiscalVisualizacaoDTO nota){

        try{
            byte[] notaPdf = gerarDanfePdf(nota);
          
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(email);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagem);          
            helper.addAttachment("nota_" + nota.getNumNota() + ".pdf", new ByteArrayResource(notaPdf));          
            
            javaMailSender.send(mimeMessage);

            System.out.println("Email enviado");
            return "Email enviado"; 
        } catch(MessagingException | IOException e){
            System.err.print("Erro no envio do email" + e.getMessage());
            return "Erro ao tentar fazer o envio do email: " + e.getMessage();
        }
    }

    public byte[] gerarDanfePdf(NotaFiscalVisualizacaoDTO nota) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, baos);

        document.open();

        Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font normal = new Font(Font.HELVETICA, 12, Font.NORMAL);


        Paragraph p = new Paragraph("DANFE - Documento Auxiliar da Nota Fiscal Eletrônica", titulo);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("Número: " + nota.getNumNota(), normal));
        document.add(new Paragraph("Tipo: " + nota.getTipoTransacao(), normal));
        document.add(new Paragraph("Data: " + nota.getFormatada(), normal));

        String clienteOuFornecedor = nota.getTipoTransacao().equals("VENDA") ? 
                                        nota.getClienteNome() : nota.getFornecedorNome();
        
        document.add(new Paragraph(clienteOuFornecedor, normal));
        document.add(new Paragraph("Valor Total: R$ " + nota.getValorTotal(), normal));
        document.add(new Paragraph("\n"));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4,2,2,2});

        table.addCell(new PdfPCell(new Phrase("Produto ", normal)));
        table.addCell(new PdfPCell(new Phrase("Quantidade ", normal)));
        table.addCell(new PdfPCell(new Phrase("Preço Unitário ", normal)));
        table.addCell(new PdfPCell(new Phrase("Total ", normal)));

        for(ItemNotaFiscalVisualizacaoDTO item : nota.getItens()){
            table.addCell(item.getProdutoNome());
            table.addCell(String.valueOf(item.getQuantidade()));
            table.addCell("R$ " + item.getPrecoUnitario());
            BigDecimal totalItem = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
            table.addCell("R$ " + String.format("%.2f", totalItem));
        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Usuário: " + nota.getUsuarioNome()));

        document.close();

        return baos.toByteArray();
    }
}
