package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//Para ver la configuración de esta clase debe de mirarse application.properties y buscar el apartado de Email
//Se utilizan dependecias nuevas incluidas en el pom.xml
//Lineas 205 - 213
public class EnvioEmailService {
    @Autowired
    private JavaMailSender mailSender;

    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail(String to, String subject, String content) {

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);
        mailSender.send(email);
        
    }
}