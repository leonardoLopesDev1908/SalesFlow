package com.example.salesflow.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
    
    private final JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}")
    private String email;

    public String enviarEmail(String destinatario, String assunto, String mensagem){

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(email);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);          
            javaMailSender.send(simpleMailMessage);
            System.out.println("Email enviado");
            return "Email enviado"; 
        }catch(Exception e){
            System.out.println("ERRO");
            System.out.println(e.getMessage());
            return "Erro ao tentar fazer o envio do email: " + e.getMessage();
        }
    }
}
